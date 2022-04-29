package com.zhang.controller;

import com.zhang.Interface.UserLoginToken;
import com.zhang.pojo.Dept;
import com.zhang.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: diexi
 * @Date: 2022/3/13 18:59
 * @ClassName: DeptController
 */
//@UserLoginToken
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService service;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/add")
    public boolean addDept(Dept dept){
        return service.addDept(dept);
    }

    /**
     * @PathVariable 获取地址栏上的参数
     * @RequestBody 只能接受json数据结构
     * @ResponseBody 将返回的数据转化为json
     * @return
     */
//    @UserLoginToken
    @GetMapping("/queryById/{deptNo}")
    public Dept queryById(@PathVariable("deptNo") Long deptNo){
        return service.queryById(deptNo);
    }

    @GetMapping("/queryAll")
    public List<Dept> queryAll(){
        return service.queryAll();
    }

    //注册进来的微服务 获取一些服务消息
    @GetMapping("/discovery")
    public Object Discovery(){
        //获取微服务清单列表
        final List<String> services = discoveryClient.getServices();
        System.out.println("discoveryClient ====" );
        final List<ServiceInstance> instances = discoveryClient.getInstances("SPRING-PROVIDER-8001");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"/t"
                    +instance.getScheme()+"/t"
                    +instance.getMetadata()+"/t"
                    +instance.getPort()+"/t"
                    +instance.getUri()+"/t"
                    +instance.getServiceId()+"/t"
            );
        }
        return  this.discoveryClient;
    }

}
