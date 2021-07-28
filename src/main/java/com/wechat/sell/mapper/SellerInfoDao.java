package com.wechat.sell.mapper;

import com.wechat.sell.bean.SellerInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SellerInfoDao {
    SellerInfo findByOpenid(String openid);
    void save(SellerInfo sellerInfo);
}
