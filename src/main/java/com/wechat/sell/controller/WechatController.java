package com.wechat.sell.controller;
import com.wechat.sell.config.ProjectUrlConfig;
import com.wechat.sell.enums.ResultEnum;
import com.wechat.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-19 9:53
 */
@Controller
@RequestMapping("/sell/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxOpenService;

    /**
     * 微信开放平台授权登录
     * @param returnUrl
     * @return
     */
    //访问：http://127.0.0.1:8080/sell/wechat/qrAuthorize?returnUrl=http://www.imooc.com
    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl){
        String url = "http://testdev.tunnel.qydev.com/sell/wechat/qrUserInfo";
        String redirectUrl = wxOpenService.buildQrConnectUrl(url,WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        log.info("微信开放平台授权获取code，redirectUrl={}",redirectUrl);
        return "redirect:" + redirectUrl;
    }

    /**
     * 微信开放平台扫码获取openid
     *
     * @param code
     * @param returnUrl
     * @return
     */
    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("[微信网页授权] {}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        //获取openid
        String openId = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl+"?openid=" + openId;
    }
}
