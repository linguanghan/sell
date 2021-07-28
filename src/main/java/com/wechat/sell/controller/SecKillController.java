package com.wechat.sell.controller;

import com.wechat.sell.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-07-02 20:50
 */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {
    @Autowired
    private SecKillService secKillService;

    /**
     * 查询秒杀活动特价商品的信息
     * @param productId
     * @return java.lang.String
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) throws Exception{
        return secKillService.querySecKillProductInfo(productId);
    }

    /**
     * 秒杀，没有抢到的话“哎呦喂，xxxx”，抢到了会返回剩余的库存量
     * @param productId
     * @author 13540
     * @date 2021-07-02 20:54
     * @return
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) throws Exception{
        secKillService.orderProductMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }
    
}
