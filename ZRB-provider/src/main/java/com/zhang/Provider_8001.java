package com.zhang;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: diexi
 * @Date: 2022/3/13 19:05
 * @ClassName: DeptProvider_8001
 */

/**
 * @EnableEurekaClient
 * @EnableDiscoveryClient
 * 简单区分下，他们在功能上是一致的：写在启动类的上，开启服务注册发现功能。
 * 不同的是，当注册中心不一样时，像：eureka、consul、zookeeper，使用是也有了区别。
 * EnableDiscoveryClient注解在common包中，通过项目的classpath来决定使用哪种实现，而EnableEurekaClient注解在netflix包中，只会使用eureka这种实现方式；
 * 所以，使用EnableDiscoverClient，对任何注册中心都适用。而EnableEurekaClient是为eureka服务的。
 * springcloud的Dalston或更早期的版本EnableEurekaClient是包含EnableDiscoverClient注解的，这种情况使用什么已经没区别了。
 */
@EnableEurekaClient
//@EnableDiscoveryClient
@SpringBootApplication
public class Provider_8001 {
    public static void main(String[] args) {
        SpringApplication.run(Provider_8001.class,args);
    }
}
