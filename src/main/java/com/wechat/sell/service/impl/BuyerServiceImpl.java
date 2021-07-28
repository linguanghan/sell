package com.wechat.sell.service.impl;

import com.wechat.sell.dto.OrderDTO;
import com.wechat.sell.enums.ResultEnum;
import com.wechat.sell.exception.SellException;
import com.wechat.sell.service.BuyerService;
import com.wechat.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-13 10:01
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {

        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【查不到该订单，orderId={}】", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOnes(orderId);
        if (orderDTO == null) {
            return null;
        }
        //是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单的openid不一致】，openid={}", openid);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }

        return orderDTO;
    }
}
