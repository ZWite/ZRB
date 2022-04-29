package com.zhang.control;

import com.zhang.Interface.UserLoginToken;
import com.zhang.pojo.User;
import com.zhang.service.DeptService;
import com.zhang.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: diexi
 * @Date: 2022/4/21 15:19
 * @ClassName: LoginControl
 */
@RestController
public class LoginControl {

    @Autowired
    private DeptService feignService;
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public void login(@RequestBody User user) {
        loginService.login(user);
    }

    @UserLoginToken
    @PostMapping("/findId/{userName}")
    public void login(@PathVariable("userName") String userName) {
        feignService.login(userName);
    }
}
