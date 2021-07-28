package com.wechat.sell.mapper;

import com.wechat.sell.bean.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductCategoryDao {
    public ProductCategory getProductCategoryById(Integer id);

    public List<ProductCategory> findAll();

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    public int save(ProductCategory productCategory);

    public int insertData(ProductCategory productCategory);
}
