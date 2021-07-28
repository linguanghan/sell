package com.wechat.sell.service;

import com.wechat.sell.dto.OrderDTO;
import org.springframework.web.bind.annotation.RestController;

public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
