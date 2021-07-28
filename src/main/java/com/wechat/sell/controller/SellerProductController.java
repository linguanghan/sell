package com.wechat.sell.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.wechat.sell.VO.ResultVO;
import com.wechat.sell.bean.ProductCategory;
import com.wechat.sell.bean.ProductInfo;
import com.wechat.sell.enums.ProductStatusEnum;
import com.wechat.sell.enums.ResultEnum;
import com.wechat.sell.exception.SellException;
import com.wechat.sell.form.ProductForm;
import com.wechat.sell.service.ProductInfoService;
import com.wechat.sell.service.impl.ProductCategoryServiceImpl;
import com.wechat.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/sell/seller/product")
@Controller
public class SellerProductController {
    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    ProductCategoryServiceImpl productCategoryService;

    /*
     * 查看所有商品
     */

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "size", defaultValue = "5") Integer pageSize,
                             Map<String, Object> map) {
        PageInfo<ProductInfo> products = productInfoService.findAll(pageNum, pageSize);
        if (products == null) {
            log.error("【没有商品】");
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        map.put("products", products);
        return new ModelAndView("product/list", map);
    }

    @GetMapping("/off_sale")
    @CacheEvict(cacheNames = "product", key = "123")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        //1. 获取该商品信息
        try {
            ProductInfo productInfo = productInfoService.findOne(productId);
            productInfo.setProductStatus(ProductStatusEnum.Down.getCode());
            productInfoService.offUpProduct(productInfo);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/up_sale")
    @CacheEvict(cacheNames = "product", key = "123")
    public ModelAndView upSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        //1. 获取该商品信息
        try {
            ProductInfo productInfo = productInfoService.findOne(productId);
            productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            productInfoService.offUpProduct(productInfo);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                      Map<String, Object> map) {
        if (!StringUtils.isNullOrEmpty(productId)) {
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        //查询所有类目
        List<ProductCategory> categories = productCategoryService.findAll();
        map.put("categoryList", categories);

        return new ModelAndView("product/index", map);
    }

    /*
     * 保存、更新
     */
    @PostMapping("/save")
//    @CachePut(cacheNames = "product", key = "123").
    // 清除缓存
//    @CacheEvict(cacheNames = "product", key = "123")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(form, productInfo);
        try {
            productInfoService.save(productInfo);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
