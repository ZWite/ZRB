package com.zhang.control;

import com.zhang.Interface.UserLoginToken;
import com.zhang.pojo.Dept;
import com.zhang.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: diexi
 * @Date: 2022/3/21 20:13
 * @ClassName: DeptController
 */
//@UserLoginToken
@RestController
public class DeptController {

    @Autowired
    private DeptService feignService;

    @Autowired
    private RestTemplate restTemplate;

    private final String PER_FIX = "http://SPRING-PROVIDER-8001";
//    private final String PER_FIX = "http://localhost:8001";

//    @RequestMapping("dept/{id}")
    public Dept getDept(@PathVariable("id") Long id){
        return restTemplate.getForObject(PER_FIX+"/dept/queryById/"+id, Dept.class);
    }

    @RequestMapping("dept/{id}")
//    @HystrixCommand(fallbackMethod = "")   配置断路器，请求失败时采用fallbackMethod配置的方法进行访问
      Dept getDeptFeign(@PathVariable("id") Long id){
        return feignService.queryById(id);
    }
}
