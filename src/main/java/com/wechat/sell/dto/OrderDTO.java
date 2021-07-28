package com.wechat.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wechat.sell.bean.OrderDetail;
import com.wechat.sell.enums.OrderStatusEnum;
import com.wechat.sell.enums.PayStatusEnum;
import com.wechat.sell.utils.EnumUtil;
import com.wechat.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-10 9:18
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL) //不传回的数据中含有null的值
public class OrderDTO {
    /**
     * 订单id.
     */
    private String orderId;
    /**
     * 买家名字.
     */
    private String buyerName;
    /**
     * 卖家手机号.
     */
    private String buyerPhone;
    /**
     * 买家地址.
     */
    private String buyerAddress;
    /**
     * 买家微信的openid.
     */
    private String buyerOpenid;
    /**
     * 订单总金额.
     */
    private BigDecimal buyerAmount;
    /**
     * 订单状态，默认为0x新下单
     */
    private Integer buyerStatus = OrderStatusEnum.NEW.getCode();
    /**
     * 支付状态默认0为未支付
     */
    private Integer payStatus;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /**
     * 一个订单对应多个商品
     */
    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(buyerStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
