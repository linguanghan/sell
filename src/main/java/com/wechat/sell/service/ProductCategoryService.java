package com.wechat.sell.service;

import com.wechat.sell.bean.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    int save(ProductCategory productCategory);
}
