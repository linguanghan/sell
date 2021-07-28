package com.wechat.sell.mapper;

import com.wechat.sell.bean.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailDao {
    List<OrderDetail> findByOrderId(String orderId);

    public void save(OrderDetail orderDetail);
}
