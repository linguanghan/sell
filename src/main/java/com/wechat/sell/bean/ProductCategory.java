package com.wechat.sell.bean;

import com.sun.javafx.beans.IDProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-08 8:51
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ProductCategory {
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
}
