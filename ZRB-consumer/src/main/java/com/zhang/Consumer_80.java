package com.zhang;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: diexi
 * @Date: 2022/3/13 19:05
 * @ClassName: DeptProvider_8001
 */

/**
 * 集成Ribbon和Eureka之后， 客户端可以不关注ip地址和端口，直接调用方法
 */
@SpringBootApplication
//服务监控中心 Eureka
@EnableEurekaClient
//远程调用fegin
@EnableFeignClients(basePackages = {"com.zhang"})
@EnableHystrix
//开启断路器  Hystrix
//@EnableCircuitBreaker
public class Consumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer_80.class,args);
    }
}
