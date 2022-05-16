package com.zhang.redis;

import com.zhang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 * @Author: diexi
 * @Date: 2022/5/16 20:11
 * @ClassName: RedisLock
 */
@Slf4j
@Configuration
public class RedisLock {

    @Autowired
    private RedisUtils redisUtils;

    /**redis官方提供的释放锁lua脚本 */
    private String RELEASE_LOCK = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
            "  return redis.call('del',KEYS[1]) else return 0 end";

    private TimeUnit lockedTimeUnit = TimeUnit.MILLISECONDS;


    public Boolean getLock(String lockName){
        Boolean flag = false;
        String json = (String)redisUtils.get(lockName);
        if(StringUtils.isNotEmpty(json)){
            flag = true;
        }
        return flag;
    }


    public Boolean lock(String lockName, String value , long time){
        boolean flag = true;
        log.info("枷锁开始：获取锁。。。。。。。");
        flag = getLock(lockName);
        System.out.println(flag);
        if (!flag){
            flag = redisUtils.setIfAbsent(lockName,value,time,lockedTimeUnit);
            log.info("枷锁结束：创建锁");
        }
        return flag;
    }

    public void releaseLock(String lockName,String single){
        redisUtils.execute(RELEASE_LOCK,lockName,single);
    }

}
