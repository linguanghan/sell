package com.wechat.sell.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-12 14:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderForm {
    /** 买家姓名 */
    @NotEmpty(message = "姓名必填")
    private String name;
    /** 买家手机 */
    @NotEmpty(message = "手机号必填")
    private String phone;
    /** 买家地址*/
    @NotEmpty(message = "地址必填")
    private String address;
    /** 买家openid*/
    @NotEmpty(message = "openid必填")
    private String openid;
    /** 购物车*/
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
