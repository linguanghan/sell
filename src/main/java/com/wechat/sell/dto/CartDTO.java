package com.wechat.sell.dto;

import lombok.Getter;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-10 10:50
 */
@Getter
public class CartDTO {
    /** ååId. */
    private String productId;

    /** æ°é. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity){
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
