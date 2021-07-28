package com.wechat.sell.controller;

import com.wechat.sell.bean.ProductCategory;
import com.wechat.sell.bean.ProductInfo;
import com.wechat.sell.form.CategoryForm;
import com.wechat.sell.service.impl.ProductCategoryServiceImpl;
import org.bouncycastle.pqc.math.linearalgebra.IntUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-18 10:37
 */
@Controller
@RequestMapping("/sell/seller/category")
public class SellerCategoryController {
    @Autowired
    ProductCategoryServiceImpl categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        ProductCategory category = categoryService.findOne(categoryId);
        map.put("category", category);
        return new ModelAndView("category/index", map);
    }

    @PostMapping("save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(form, productCategory);
        try {
            categoryService.save(productCategory);
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
