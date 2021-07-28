package com.wechat.sell.controller;

import com.wechat.sell.VO.ResultVO;
import com.wechat.sell.bean.UserInfo;
import com.wechat.sell.service.UserService;
import com.wechat.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 * @author 13540
 * @version 1.0
 * @date 2021-03-15 8:45
 */
@Slf4j
@RequestMapping("/sell/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResultVO login(HttpServletResponse response, @RequestParam("username") String username,
                          @RequestParam("pass") String pass) {

        UserInfo user = new UserInfo(username, pass);
        //1. 判断用户名是否存在
        if (!userService.userExistOrNot(username)) {
            log.error("用户名不存在");
            return ResultVOUtil.error(0, "用户名不存在");
//            throw new SellException(ResultEnum.USER_NOT_EXIST);
        }

        if (!userService.checkUser(user)) {
            log.error("密码错误");
            return ResultVOUtil.error(1, "密码错误");
        }
        Cookie cookie = new Cookie("openid", username);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResultVOUtil.success(2, "登录成功");
    }
}
