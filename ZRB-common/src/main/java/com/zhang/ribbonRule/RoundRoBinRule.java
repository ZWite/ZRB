package com.zhang.ribbonRule;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

/**
 * 自定义轮询策略
 * @author qi
 */
public class RoundRoBinRule {

    @Bean
    public RandomRule ribbonRule(){
        return RoundRoBinRule.getInstances();
    }

    //提供实例获取方法
    public static RandomRule getInstances(){
        return new RandomRule();
    }




}
