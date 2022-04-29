package com.zhang;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: diexi
 * @Date: 2022/3/21 20:34
 * @ClassName: BeanUtils
 */
@Configuration
public class BeanUtils {


    //配置负载均衡实现RestTemplate
    //IRule

    /**
     * IRule
     * AbstractLoadBalancerRule : 会先过滤掉跳闸，访问故障的服务，对剩下的进行轮询
     * RoundRoBinRule : 轮询
     * RandomRule : 随机
     * RetryRule : 重试   会先按照轮询获取服务，如果服务获取失败，会在指定时间内重试
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
