package com.wechat.sell.service;

import com.github.pagehelper.PageInfo;
import com.wechat.sell.bean.ProductInfo;
import com.wechat.sell.dto.CartDTO;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductInfoService {
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    PageInfo<ProductInfo> findAll(int pageNum, int pageSize);

    int save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上下架
    int offUpProduct(ProductInfo productInfo);

}
