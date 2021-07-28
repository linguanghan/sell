package com.wechat.sell.mapper;

import com.wechat.sell.bean.ProductCategory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//@RunWith(SpringRunner.class)
@SpringBootTest
class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void getBeanById(){
        ProductCategory category = productCategoryDao.getProductCategoryById(1);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<ProductCategory> byCategoryTypeIn = productCategoryDao.findByCategoryTypeIn(list);
        System.out.println(category);
        System.out.println("==============");
        System.out.println(byCategoryTypeIn);
    }
}