package com.wechat.sell.handler;

import com.wechat.sell.config.ProjectUrlConfig;
import com.wechat.sell.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-26 19:55
 */
@ControllerAdvice
public class BuyerExceptionHandler {
//    @Autowired
//    private ProjectUrlConfig projectUrlConfig;
    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizedException() {
        System.out.println("1111111111");
        return new ModelAndView("redirect:/sell/user/login");
    }

    //全局异常处理

}
