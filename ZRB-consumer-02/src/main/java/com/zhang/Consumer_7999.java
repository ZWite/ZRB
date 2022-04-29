package com.zhang;


import com.zhang.ribbonRule.RoundRoBinRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: diexi
 * @Date: 2022/3/13 19:05
 * @ClassName: DeptProvider_8001
 */

/**
 * 集成Ribbon和Eureka之后， 客户端可以不关注ip地址和端口，直接调用方法
 * @author 1
 */
@SpringBootApplication
//服务监控中心 Eureka
@EnableEurekaClient
//远程调用fegin
@EnableFeignClients(basePackages = {"com.zhang"})
@EnableHystrix
//开启断路器  Hystrix
//@EnableCircuitBreaker
//开启Ribbon 负载均衡
//@RibbonClient(name = "ribbon-client",configuration = RoundRoBinRule.class)
public class Consumer_7999 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer_7999.class,args);
    }
}
