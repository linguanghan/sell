package com.wechat.sell.service.impl;

import com.github.pagehelper.PageInfo;
import com.wechat.sell.bean.OrderDetail;
import com.wechat.sell.dto.OrderDTO;
import com.wechat.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "1101110";

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("廖师兄");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerPhone("1234534324");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123458");
        o2.setProductQuantity(2);
        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("[创建订单] result={}", result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOnes("1615356957773800698");
        System.out.println(orderDTO);
    }

    @Test
    public void findList() throws Exception {
        PageInfo<OrderDTO> list = orderService.findList("1101110", 1, 4);
        System.out.println(list);
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOnes("1615517795975911325");
        OrderDTO cancel = orderService.cancel(orderDTO);
        System.out.println(cancel);
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOnes("1615517872896355795");
        OrderDTO cancel = orderService.finish(orderDTO);
        System.out.println(cancel);
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOnes("1615356957773800698");
        OrderDTO cancel = orderService.paid(orderDTO);
        System.out.println(cancel);
    }

    @Test
    public void findAll() {
        PageInfo<OrderDTO> list = orderService.findList(1, 4);
        System.out.println(list);
    }
}