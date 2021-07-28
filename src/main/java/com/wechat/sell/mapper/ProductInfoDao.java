package com.wechat.sell.mapper;

import com.wechat.sell.bean.ProductInfo;
import com.wechat.sell.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-08 14:16
 */
@Mapper
public interface ProductInfoDao {
    ProductInfo findOne(String productId);

    int insertProduct(ProductInfo productInfo);

    List<ProductInfo> findByProductStatus(Integer productStatus);


    List<ProductInfo> findAll();

    void updateStock(ProductInfo productInfo);

    int updateProductStatus(ProductInfo productInfo);

    int update(ProductInfo productInfo);
}
