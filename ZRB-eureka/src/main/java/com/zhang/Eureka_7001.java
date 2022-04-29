package com.zhang;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: diexi
 * @Date: 2022/3/13 19:05
 * @ClassName: DeptProvider_8001
 */
@EnableEurekaServer
@SpringBootApplication
public class Eureka_7001 {
    public static void main(String[] args) {
        SpringApplication.run(Eureka_7001.class,args);
    }
}
