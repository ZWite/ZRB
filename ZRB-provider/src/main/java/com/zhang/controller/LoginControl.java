package com.zhang.controller;

import com.zhang.ThreadLocal.ContextManager;
import com.zhang.ThreadLocal.ThreadLocalData;
import com.zhang.pojo.User;
import com.zhang.redis.RedisUtils;
import com.zhang.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

import static com.zhang.jwtToken.JwtToken.createJToken;

/**
 * @Author: diexi
 * @Date: 2022/3/26 19:01
 * @ClassName: LoginControl
 */
@RestController
public class LoginControl {

    @Autowired
    private LoginService service;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/login")
    public void login(@RequestBody User user) {
        String countUser = service.getCountUser(user);
        User contextUser = service.findUserById(user.getUserName());
        String jToken = createJToken(user.getUserName());
        System.out.println(jToken);
        redisUtils.set(jToken, contextUser, 1, TimeUnit.HOURS);
//        redisUtils.setHash(User.class.getName(), user.getUserName(), jToken);
        Object hash = redisUtils.getHash(User.class.getName(), user.getUserName());
        System.out.println(hash);
//        ContextManager.setContextData(User.class,contextUser);
    }

    @PostMapping("/findId/{userName}")
    public void login(@PathVariable("userName") String userName) {
        User contextData = ContextManager.getContextData(User.class);
        System.out.println(contextData.toString());
        User findUserById = service.findUserById(userName);
        System.out.println(findUserById);
    }
}
