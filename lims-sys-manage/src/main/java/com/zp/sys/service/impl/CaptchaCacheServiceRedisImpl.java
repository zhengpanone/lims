package com.zp.sys.service.impl;

import com.anji.captcha.service.CaptchaCacheService;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Author : zhengpanone
 * Date : 2024/4/25 18:42
 * Version : v1.0.0
 * Description: 自定义redis验证码缓存实现类
 * https://github.com/Harries/springboot-demo/blob/master/Captcha/src/main/java/com/et/captcha/service/CaptchaCacheServiceRedisImpl.java
 */
public class CaptchaCacheServiceRedisImpl implements CaptchaCacheService {
    private StringRedisTemplate stringRedisTemplate;

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String key, String value, long expiresInSeconds) {
        stringRedisTemplate.opsForValue().set(key, value, expiresInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Long increment(String key, long val) {
        return stringRedisTemplate.opsForValue().increment(key, val);
    }

    @Override
    public String type() {
        return "redis";
    }
}
