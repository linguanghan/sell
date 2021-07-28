package com.wechat.sell.mapper;

import com.wechat.sell.bean.SellerInfo;
import com.wechat.sell.utils.KeyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SellerInfoDaoTest {
    @Autowired
    SellerInfoDao sellerInfoDao;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        sellerInfoDao.save(sellerInfo);
    }

    @Test
    public void findByOpenid(){
        SellerInfo sellerInfo = sellerInfoDao.findByOpenid("abc");
        System.out.println(sellerInfo);
    }
}