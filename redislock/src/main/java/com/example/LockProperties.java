package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by tangzhilong on 17/4/26.
 */
@ConfigurationProperties(prefix = "com.tzl.lock")
public class LockProperties {
    private Long expireTime;

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
