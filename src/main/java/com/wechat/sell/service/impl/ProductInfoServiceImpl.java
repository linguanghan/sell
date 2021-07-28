package com.wechat.sell.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.wechat.sell.bean.ProductInfo;
import com.wechat.sell.dto.CartDTO;
import com.wechat.sell.enums.ProductStatusEnum;
import com.wechat.sell.enums.ResultEnum;
import com.wechat.sell.exception.SellException;
import com.wechat.sell.mapper.ProductInfoDao;
import com.wechat.sell.service.ProductInfoService;
import com.wechat.sell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoDao productInfoDao;

    @Override
    @Cacheable(cacheNames = "product", key = "123")
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public PageInfo<ProductInfo> findAll(int pageNum, int pageSize) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<ProductInfo> allPro = productInfoDao.findAll();
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(allPro);
//        pageInfo.setPages(page.getPages());
//        pageInfo.setTotal(page.getTotal());
//        pageInfo.setPageNum(page.getPageNum());
//        pageInfo.setPageSize(page.getPageSize());

        return pageInfo;
    }

    @Override

    @CacheEvict(cacheNames = "product", key = "123")
//    @CachePut(cacheNames = "product", key = "123")
    public int save(ProductInfo productInfo) {
        if(StringUtils.isNullOrEmpty(productInfo.getProductId())){
            String productId = KeyUtil.genUniqueKey();
            productInfo.setProductId(productId);
            productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            return productInfoDao.insertProduct(productInfo);
        }
        return productInfoDao.update(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoDao.updateStock(productInfo);
        }
    }


    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoDao.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoDao.updateStock(productInfo);
        }
    }

    @Override
    @Transactional
    public int offUpProduct(ProductInfo productInfo) {
        return productInfoDao.updateProductStatus(productInfo);
    }

}
