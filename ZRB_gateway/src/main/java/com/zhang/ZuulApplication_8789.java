package com.zhang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: diexi
 * @Date: 2022/5/4 18:46
 * @ClassName: ZuulApplicaiton
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulApplication_8789 {
    public static void main(String[] args){
        SpringApplication.run(ZuulApplication_8789.class,args);
    }
}
