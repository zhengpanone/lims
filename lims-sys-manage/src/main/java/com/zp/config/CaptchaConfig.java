package com.zp.config;

import com.anji.captcha.properties.AjCaptchaProperties;
import com.anji.captcha.service.CaptchaCacheService;
import com.anji.captcha.service.impl.CaptchaServiceFactory;
import com.zp.sys.service.impl.CaptchaCacheServiceRedisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Author : zhengpanone
 * Date : 2024/4/25 18:26
 * Version : v1.0.0
 * Description: TODO
 */
@Configuration
public class CaptchaConfig {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Bean(name = "captchaCacheService")
    @Primary
    public CaptchaCacheService captchaCacheService(AjCaptchaProperties config) {
        CaptchaCacheService cache = CaptchaServiceFactory.getCache(config.getCacheType().name());
        if (cache instanceof CaptchaCacheServiceRedisImpl) {

        }
        return null;
    }
}
