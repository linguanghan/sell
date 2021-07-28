package com.wechat.sell.bean;

import com.wechat.sell.enums.OrderStatusEnum;
import com.wechat.sell.enums.ProductStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-08 9:03
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderMaster {
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
    private Date createTime;
    private Date updateTime;
}
