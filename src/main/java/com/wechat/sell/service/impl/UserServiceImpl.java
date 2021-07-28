package com.wechat.sell.service.impl;

import com.wechat.sell.bean.UserInfo;
import com.wechat.sell.mapper.UserDao;
import com.wechat.sell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-15 10:14
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public boolean userExistOrNot(String username) {
        if (userDao.getUsername(username) <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean checkUser(UserInfo userInfo) {
        UserInfo userInfoByUsername = userDao.getUserInfoByUsername(userInfo.getUsername());
        if (userInfo.getPass().equals(userInfoByUsername.getPass())) {
            return true;
        }
        return false;
    }


}
