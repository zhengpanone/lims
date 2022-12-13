package com.zp.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * RedisConfig
 *
 * @author zhengpanone
 * @since 2022-11-18
 */
@Component
public class RedisConfig {
    @Bean(name = "template")
    public RedisTemplate<String,Object> template(RedisConnectionFactory factory){
        // 创建RedisTemplate<String, Object>对象
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 配置连接工厂
        redisTemplate.setConnectionFactory(factory);
        // 定义Jackson2JsonRedisSerializer序列化对象
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerial  = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会报异常
        om.activateDefaultTyping(

                LaissezFaireSubTypeValidator.instance ,
                ObjectMapper.DefaultTyping.NON_FINAL,

                JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2JsonRedisSerial.setObjectMapper(om);
        StringRedisSerializer stringSerial = new StringRedisSerializer();
        // redis key 序列化方式使用stringSerial
        redisTemplate.setKeySerializer(stringSerial);
        // redis value 序列化方式使用jackson
        redisTemplate.setValueSerializer(jackson2JsonRedisSerial);
        // redis hash key 序列化方式使用stringSerial
        redisTemplate.setHashKeySerializer(stringSerial);
        // redis hash value 序列化方式使用jackson
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerial);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;

    }
}
