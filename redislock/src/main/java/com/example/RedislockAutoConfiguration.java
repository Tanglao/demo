package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tangzhilong on 17/4/26.
 */
@Configuration
@EnableConfigurationProperties(LockProperties.class)
public class RedislockAutoConfiguration {

    private RedisLockService redisLockService;
    @Autowired
    private LockProperties lockProperties;

    @Bean
    public RedisLockService redisLockService() {
        return new RedisLockService(lockProperties);
    }


}
