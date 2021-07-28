package com.wechat.sell.mapper;

import com.wechat.sell.bean.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductInfoDaoTest {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void test() {
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setProductId("123456");
//        productInfo.setProductName("皮蛋瘦肉粥");
//        productInfo.setProductPrice(new BigDecimal(3.2));
//        productInfo.setProductStock(100);
//        productInfo.setProductDiscription("很好喝的粥");
//        productInfo.setProductIcon("http://XXXX.jpg");
//        productInfo.setProductStatus(0);
//        productInfo.setCategoryType(2);
//
//        productInfoDao.insertProduct(productInfo);
        List<ProductInfo> byProductStatus = productInfoDao.findByProductStatus(0);
        byProductStatus.forEach(System.out::println);
    }
}