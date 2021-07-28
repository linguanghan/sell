package com.wechat.sell.mapper;

import com.wechat.sell.bean.OrderMaster;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-09 13:43
 */
@Mapper
public interface OrderMasterDao {
    public List<OrderMaster> findAll();

    public List<OrderMaster> findByBuyerOpenId(String openId);

    public OrderMaster findOneByOrderId(String orderId);

    public void save(OrderMaster orderMaster);

    public int update(OrderMaster orderMaster);

}
