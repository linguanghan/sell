package com.wechat.sell.mapper;

import com.wechat.sell.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-15 10:01
 */
@Mapper
public interface UserDao {
    public  int getUsername(String username);

    public UserInfo getUserInfoByUsername(String username);
}
