package com.zhang.service;

import com.zhang.hystrix.Hystrix;
import com.zhang.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: diexi
 * @Date: 2022/3/23 14:09
 * @ClassName: DeptService
 */
@Component
@FeignClient(name = "SPRING-PROVIDER-8001",fallback = Hystrix.class)
public interface LoginService {
    @GetMapping("/login")
    void login(@RequestBody User user);

}
