package com.example;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tangzhilong on 17/4/26.
 */
public class RedisLockService {

    private RedisSerializer serializer;


    @Autowired
    private RedisTemplate redisTemplate;
    private LockProperties lockProperties;

    private ReentrantLock lock;

    public RedisLockService(LockProperties lockProperties) {
        this.lockProperties=lockProperties;
    }

    @PostConstruct
    private void init() {
        this.serializer = new StringRedisSerializer();
        this.lock = new ReentrantLock(true);
    }

    public boolean getLock(final String businessKey) {
        Object obj = null;

        final long currentTime = System.currentTimeMillis() + lockProperties.getExpireTime();
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    Boolean success = connection.setNX(businessKey.getBytes(),serializer.serialize(String.valueOf(currentTime)));
                    connection.close();
                    return success;
                }
            });
        } catch (Exception e) {
            LogFactory.getLog(this.getClass()).error("setNX redis error, key : {}", e);
        }
        return obj != null ? (Boolean) obj : false;
    }

    public synchronized boolean releaseLock(String businessKey) {

        redisTemplate.delete(businessKey);
        return true;
    }
}
