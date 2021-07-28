package com.wechat.sell.aspect;
import com.wechat.sell.constant.CookieConstant;
import com.wechat.sell.exception.SellerAuthorizeException;
import com.wechat.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Aspect
@Component
@Slf4j
public class UserAuthorizeAspect {
    @Autowired

    @Pointcut("execution(public * com.wechat.sell.controller.Buyer*.*(..))")
    public void verify() {

    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        HttpServletResponse response = attributes.getResponse();
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID);
        if (cookie == null) {
            log.warn("【登录校验cookie中查不到token】");
            throw new SellerAuthorizeException();
        }
    }
}
