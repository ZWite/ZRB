package com.zhang.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author: diexi
 * @Date: 2022/3/27 18:17
 * @ClassName: RedisUtils
 */
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

/**
 * redisTemplate
 * opsForValue 操作字符串 类似String
 * opsForList 操作List 类似List
 * opsForSet
 * opsForHash
 * opsForZSet
 * opsForGeo
 */


    /**
     * 指定缓存失效时间
     *
     * @param key      键
     * @param time     时间
     * @param timeUnit 时间单位
     * @return
     */
    public boolean expire(String key, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, timeUnit);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

//============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }



    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key      键
     * @param value    值
     * @param time     时间 time要大于0 如果time小于等于0 将设置无限期
     * @param timeUnit 时间单位
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入 Map类型
     *
     * @param key 键
     * @param HK  键
     * @param HV  值
     * @return true成功 false失败
     */
    public boolean setHash(String key, Object HK, Object HV) {
        try {
            redisTemplate.opsForHash().put(key, HK, HV);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入 Map类型
     *
     * @param key 键
     * @param HK  键
     * @return true成功 false失败
     */
    public Object getHash(String key, Object HK) {
        try {
            Object o = redisTemplate.opsForHash().get(key, HK);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除key
     *
     * @param key 键
     * @return true成功 false失败
     */
    public Object delete(String key) {
        Boolean flag = true;
        try {
            flag = redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return flag;
    }


    /**
     * 分布式锁
     *
     * @param key      键
     * @param value    键
     * @param time     时间long
     * @param timeUnit 时间格式
     * @return true成功 false失败
     */
    public Boolean setIfAbsent(String key, String value, long time, TimeUnit timeUnit) {
        Boolean flag = true;
        try {
            flag = redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    /**
     * @param script lua脚本
     * @param key    键
     * @param single  唯一标识
     * @return true成功 false失败
     */
    public Object execute(String script, String key, String single) {
        Long lock;
        try {
            lock = redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList(key),single);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lock;
    }


}
