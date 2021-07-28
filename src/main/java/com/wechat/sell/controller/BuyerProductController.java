package com.wechat.sell.controller;

import com.wechat.sell.VO.ProductInfoVO;
import com.wechat.sell.VO.ProductVO;
import com.wechat.sell.VO.ResultVO;
import com.wechat.sell.bean.ProductCategory;
import com.wechat.sell.bean.ProductInfo;
import com.wechat.sell.service.ProductCategoryService;
import com.wechat.sell.service.ProductInfoService;
import com.wechat.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/sell/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
//    @Cacheable(cacheNames = "product", key = "123")
    public ResultVO list() {
        //1. 查询所有的上架商品
        List<ProductInfo> upAll = productInfoService.findUpAll();
        //2. 查询类目（一次性查询）
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : upAll) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        //3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo:upAll){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
//                    productInfoVO.setProductId(productInfo.getProductId());
//                    productInfoVO.setProductName(productInfo.getProductName());
//                    productInfoVO.setProductDescription(productInfo.getProductDescription());
//                    productInfoVO.setProductPrice(productInfo.getProductPrice());
//                    productInfoVO.setProductIcon(productInfo.getProductIcon());
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
