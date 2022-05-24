package com.zhang.lock;


import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson 分布式锁
 * @Author: qi
 * @Date: 2022/5/4 20:50
 * @ClassName: RedisLock
 */

@Configuration
public class RedissonLock {


    /**
     * @SneakyThrows 该注解是为了隐式的抛出check Exception,避免javac强制异常处理，所以异常向上转型为Throwable
     * @return
     */
    @SneakyThrows
    @Bean("redisson")
    public RedissonClient redissonLock(){
//        Config config = new Config();
        //设置单机
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        RedissonClient redisson = Redisson.create(config);
        /**
         * 从配置文件读取
         */
        Config config = Config.fromYAML(RedissonLock.class.getClassLoader().getResource("redisson.yml"));
        return  Redisson.create(config);
    }
}
