package com.wechat.sell.controller;

import com.wechat.sell.bean.TestsBean;
import com.wechat.sell.service.TestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestsController {
    @Autowired
    TestsService testsService;

    @ResponseBody
    @GetMapping("/test")
    public TestsBean test(@RequestParam("id")Integer id){
        return testsService.getBean(id);
    }
}
