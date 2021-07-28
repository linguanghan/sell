package com.wechat.sell.service.impl;

import com.wechat.sell.bean.ProductCategory;
import com.wechat.sell.mapper.ProductCategoryDao;
import com.wechat.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-08 10:13
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryDao.getProductCategoryById(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public int save(ProductCategory productCategory) {
        if (productCategory.getCategoryId() == null) {
            return productCategoryDao.insertData(productCategory);
        }
        return productCategoryDao.save(productCategory);
    }
}
