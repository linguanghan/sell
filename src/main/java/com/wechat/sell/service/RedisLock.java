package com.wechat.sell.service;

import com.mysql.jdbc.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

/**
 * TODO
 * redis实现分布式锁：当前时间+超时时间
 *
 * @author 13540
 * @version 1.0
 * @date 2021-07-02 22:37
 */
@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 加锁
     * @param key
     * @param value 当前时间+超时时间
     * @author 13540
     * @date 2021-07-02 22:49
     * @return boolean
     */
    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        String currentValue = redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmptyOrWhitespaceOnly(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmptyOrWhitespaceOnly(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    public void unlock(String key, String value){

        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmptyOrWhitespaceOnly(currentValue) && currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【Redis分布式锁】解锁异常，{}", e);
        }
    }
}
