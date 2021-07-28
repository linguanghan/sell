package com.wechat.sell.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.wechat.sell.bean.OrderDetail;
import com.wechat.sell.bean.OrderMaster;
import com.wechat.sell.bean.ProductInfo;
import com.wechat.sell.converter.OrderMaster2OrderDTO;
import com.wechat.sell.dto.CartDTO;
import com.wechat.sell.dto.OrderDTO;
import com.wechat.sell.enums.OrderStatusEnum;
import com.wechat.sell.enums.PayStatusEnum;
import com.wechat.sell.enums.ResultEnum;
import com.wechat.sell.exception.SellException;
import com.wechat.sell.mapper.OrderDetailDao;
import com.wechat.sell.mapper.OrderMasterDao;
import com.wechat.sell.service.OrderService;
import com.wechat.sell.service.ProductInfoService;
import com.wechat.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1. 查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. 计算总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailDao.save(orderDetail);
        }

        //3. 写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setBuyerAmount(orderAmount);
        orderMaster.setBuyerStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderMasterDao.save(orderMaster);

        //4. 扣库存
        //获取订单列表
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOnes(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findOneByOrderId(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIT);
        }
        OrderDTO orderDTO = new OrderDTO();

        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        orderDTO.setBuyerAmount(orderMaster.getBuyerAmount());
        return orderDTO;
    }

    @Override
    public PageInfo<OrderDTO> findList(String buyerOpenid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        List<OrderMaster> orderMasterList = orderMasterDao.findByBuyerOpenId(buyerOpenid);
//        if (CollectionUtils.isEmpty(orderMasterList)) {
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//        for (OrderMaster orderMaster : orderMasterList) {
//            OrderDTO orderDTO = new OrderDTO();
//            BeanUtils.copyProperties(orderMaster, orderDTO);
//            orderDTO.setBuyerAmount(orderMaster.getBuyerAmount());
//            String orderId = orderMaster.getOrderId();
//            List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
//            orderDTO.setOrderDetailList(orderDetailList);
//            orderDTOList.add(orderDTO);
//        }
        orderDTOList = OrderMaster2OrderDTO.convertList(orderMasterList);
        PageInfo<OrderDTO> pageInfo = new PageInfo<>(orderDTOList);
        return pageInfo;
    }


    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        //判断订单状态
        if (orderDTO.getBuyerStatus().equals(OrderStatusEnum.CANCEL.getCode())) {
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getBuyerStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMaster.setBuyerStatus(OrderStatusEnum.CANCEL.getCode());
        int update = orderMasterDao.update(orderMaster);
        if (update == 0) {
            log.error("【取消订单更新失败】orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中没有商品详情");
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);
        //如果已支付，需要退款

        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getPayStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确，orderId={}，orderStatus={}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setBuyerStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int update = orderMasterDao.update(orderMaster);
        if (update == 0) {
            log.error("【完结订单更新失败】orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        //判断订单状态
        if (!orderDTO.getPayStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确，orderId={}，getPayStatus={}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付完成】订单支付状态不正确，getPayStatus={}", orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        //修改订单状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int update = orderMasterDao.update(orderMaster);
        if (update == 0) {
            log.error("【订单支付更新失败】orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public PageInfo<OrderDTO> findList(int pageNum, int pageSize) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<OrderMaster> orderMasterList = orderMasterDao.findAll();

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convertList(orderMasterList);

        PageInfo<OrderDTO> pageInfo = new PageInfo<>(orderDTOList);
//        pageInfo.setList(orderDTOList);
        pageInfo.setPages(page.getPages());
        pageInfo.setTotal(page.getTotal());
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public List<OrderDetail> findOrderDetailById(String OrderId) {
        List<OrderDetail> details = orderDetailDao.findByOrderId(OrderId);
        if(CollectionUtils.isEmpty(details)){
            log.error("【订单详情不存在】");
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIT);
        }
        return details;
    }


}
