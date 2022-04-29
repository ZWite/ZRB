package com.zhang.service;

import com.zhang.pojo.User;

/**
 * @Author: diexi
 * @Date: 2022/3/27 0:08
 * @ClassName: LoginMapper
 */
public interface LoginService {

    String getCountUser(User user);

    User findUserById(String userName);
}
