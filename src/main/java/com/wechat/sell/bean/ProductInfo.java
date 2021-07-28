package com.wechat.sell.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wechat.sell.enums.OrderStatusEnum;
import com.wechat.sell.enums.ProductStatusEnum;
import com.wechat.sell.utils.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-08 8:55
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 6085742813788258208L;

    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    /** 状态，0正常1下架. */
    private Integer productStatus;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
