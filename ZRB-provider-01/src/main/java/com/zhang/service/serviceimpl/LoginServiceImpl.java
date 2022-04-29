package com.zhang.service.serviceimpl;

import com.zhang.mapper.LoginMapper;
import com.zhang.pojo.User;
import com.zhang.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: diexi
 * @Date: 2022/3/27 0:08
 * @ClassName: LoginMapper
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper mapper;

    @Override
    public String getCountUser(User user) {
        return mapper.getCountUser(user);
    }

    @Override
    public User  findUserById(String userName) {
        return mapper.findUserById(userName);
    }
}
