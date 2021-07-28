package com.wechat.sell.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-08 8:59
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDetail {
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;
    private Date createTime;
    private Date updateTime;
}
