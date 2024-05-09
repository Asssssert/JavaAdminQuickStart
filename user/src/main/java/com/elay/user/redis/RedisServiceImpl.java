package com.elay.user.redis;

import com.elay.infra.constant.RedisConstants;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    public Boolean hasKey(String key) {
        Boolean hasKey = redisTemplate.hasKey(key);
        return hasKey;
    }

    @Override
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    @Override
    public void del(Collection keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public void deleteByPrex(String key) {
        Set keys = redisTemplate.keys(key + "*");
        redisTemplate.delete(keys);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, RedisConstants.REDIS_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    @Override
    public void flush() {
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }
}
