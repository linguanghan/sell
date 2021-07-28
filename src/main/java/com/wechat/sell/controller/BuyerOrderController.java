package com.wechat.sell.controller;

import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.sun.org.apache.regexp.internal.RE;
import com.wechat.sell.VO.ResultVO;
import com.wechat.sell.converter.OrderForm2OrderDTOConverter;
import com.wechat.sell.dto.OrderDTO;
import com.wechat.sell.enums.ResultEnum;
import com.wechat.sell.exception.SellException;
import com.wechat.sell.form.OrderForm;
import com.wechat.sell.service.BuyerService;
import com.wechat.sell.service.impl.OrderServiceImpl;
import com.wechat.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-12 14:09
 */
@RestController
@RequestMapping("/sell/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (orderDTO == null) {
            log.error("【购物车】不能为空：orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultVOUtil.success(map);
    }


    //订单列表
    @GetMapping("/list")
    public ResultVO<PageInfo<OrderDTO>> orderList(@RequestParam("openid") String buyerOpenid,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        if (StringUtils.isEmptyOrWhitespaceOnly(buyerOpenid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageInfo<OrderDTO> list = orderService.findList(buyerOpenid, pageNum, pageSize);
        return ResultVOUtil.success(list);
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        //TODO 不安全做法，改进
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
//        orderService.cancel(orderId);
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }
}
