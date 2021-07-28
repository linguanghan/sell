package com.wechat.sell.service;

import com.wechat.sell.bean.UserInfo;

public interface UserService {
    public boolean userExistOrNot(String username);

    public boolean checkUser(UserInfo userInfo);
}
