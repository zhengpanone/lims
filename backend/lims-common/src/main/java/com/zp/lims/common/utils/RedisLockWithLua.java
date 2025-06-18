package com.zp.lims.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;

@Component
public class RedisLockWithLua {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String LOCK_PREFIX = "lock:";

    // 加锁
    public boolean tryLock(String key, String value, Duration expire) {
        return Boolean.TRUE.equals(
                redisTemplate.opsForValue().setIfAbsent(LOCK_PREFIX + key, value, expire)
        );
    }

    // 解锁（Lua 脚本）
    public boolean unlock(String key, String value) {
        String luaScript =
                "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                        "   return redis.call('del', KEYS[1]) " +
                        "else " +
                        "   return 0 " +
                        "end";

        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText(luaScript);
        script.setResultType(Long.class);

        Long result = redisTemplate.execute(script, Collections.singletonList(LOCK_PREFIX + key), value);
        return result == 1L;
    }
}
