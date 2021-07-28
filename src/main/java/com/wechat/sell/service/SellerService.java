package com.wechat.sell.service;

import com.wechat.sell.bean.SellerInfo;
import org.springframework.stereotype.Service;

public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
