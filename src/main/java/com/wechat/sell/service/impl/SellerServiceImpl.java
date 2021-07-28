package com.wechat.sell.service.impl;

import com.wechat.sell.bean.SellerInfo;
import com.wechat.sell.mapper.SellerInfoDao;
import com.wechat.sell.service.SellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-19 9:16
 */

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }
}
