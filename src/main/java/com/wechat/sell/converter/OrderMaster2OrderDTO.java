package com.wechat.sell.converter;

import com.wechat.sell.bean.OrderMaster;
import com.wechat.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-12 10:11
 */
public class OrderMaster2OrderDTO {
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convertList(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }

}
