package com.wechat.sell.service;

public interface SecKillService {
    String querySecKillProductInfo(String productId);

    void orderProductMockDiffUser(String productId);
}
