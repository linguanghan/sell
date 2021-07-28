package com.wechat.sell.form;

import com.wechat.sell.bean.ProductCategory;
import lombok.Data;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-18 9:32
 */
@Data
public class ProductForm {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus;
    private Integer categoryType;

//    private ProductCategory productCategory;
}
