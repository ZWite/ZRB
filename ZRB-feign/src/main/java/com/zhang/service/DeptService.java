package com.zhang.service;

import com.netflix.loadbalancer.RandomRule;
import com.zhang.pojo.Dept;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: diexi
 * @Date: 2022/3/23 14:09
 * @ClassName: DeptService
 */
@Component
@FeignClient(value = "SPRING-PROVIDER-8001",contextId ="DeptService",configuration = RandomRule.class)
public interface DeptService {

    @GetMapping("/dept/queryById/{id}")
    Dept queryById(@PathVariable("id") Long id);



    @PostMapping("/findId/{userName}")
    void login(@PathVariable("userName") String userName);
}
