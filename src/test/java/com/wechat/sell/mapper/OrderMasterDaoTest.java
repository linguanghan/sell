package com.wechat.sell.mapper;

import com.wechat.sell.bean.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterDaoTest {
    @Autowired
    OrderMasterDao orderMasterDao;

    @Test
    public void test() {
        List<OrderMaster> all = orderMasterDao.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        OrderMaster orderMaster = new OrderMaster("1234", "lgh", "15359284261", "上海", "lgh5566", new BigDecimal(10), 0, 0, null, null);
        orderMasterDao.save(orderMaster);
    }

    @Test
    public void testFindByOpenId() {
        List<OrderMaster> orderMasters = orderMasterDao.findByBuyerOpenId("lgh5566");
        orderMasters.forEach(System.out::println);
    }
}