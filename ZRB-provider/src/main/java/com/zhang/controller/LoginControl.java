package com.zhang.controller;

import com.zhang.Interface.ServiceAop;
import com.zhang.StringUtils;
import com.zhang.ThreadLocal.ContextManager;
import com.zhang.UserUtils.UUtils;
import com.zhang.assertUtils.AssertUtils;
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

    private static String yz ="wyz980622";

    @Autowired
    private LoginService service;

    @Autowired
    private RedisUtils redisUtils;

    @ServiceAop(name = "login")
    @PostMapping("/login")
    public void login(@RequestBody User user) {
        Object result = redisUtils.get(UUtils.generateOnlyUserSignleEncryption(user));
//        Object result = redisUtils.getHash(User.class.getName(),user.getUserName());
        if(result == null) {
            String countUser = service.getCountUser(user);
            AssertUtils.isNull(StringUtils.isEmpty(countUser),"请检查账户名密码是否一致");
//            User contextUser = service.findUserById(user.getUserName());
            String jToken = createJToken(user.getUserName());
            redisUtils.set(UUtils.generateOnlyUserSignleEncryption(user), jToken, 1, TimeUnit.HOURS);
//            redisUtils.setHash(User.class.getName(), user.getUserName(), jToken);
        }
    }

    @GetMapping("/findId/{userName}")
    public void login(@PathVariable("userName") String userName) {
        User contextData = ContextManager.getContextData(User.class);
        System.out.println(contextData.toString());
        User findUserById = service.findUserById(userName);
        System.out.println(findUserById);
    }
}
