package com.wechat.sell.service;

import com.github.pagehelper.PageInfo;
import com.wechat.sell.bean.OrderDetail;
import com.wechat.sell.bean.ProductInfo;
import com.wechat.sell.dto.OrderDTO;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-10 9:14
 */
public interface OrderService {
    /** 创建订单*/
    OrderDTO create(OrderDTO orderDTO);
    /** 查询单个订单*/
    OrderDTO findOnes(String orderId);
    /** 查询订单列表*/
    PageInfo<OrderDTO> findList(String buyerOpenid, int pageNum, int pageSize);
    /** 取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);
    /** 完结订单*/
    OrderDTO finish(OrderDTO orderDTO);
    /** 支付订单*/
    OrderDTO paid(OrderDTO orderDTO);
    /** 查询所有订单*/
    PageInfo<OrderDTO> findList(int pageNum, int pageSize);
    /** 根据订单id查询订单详情*/
    List<OrderDetail> findOrderDetailById(String OrderId);

}
