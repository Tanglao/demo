package com.tzl.services;

import com.tzl.bean.User;
import com.tzl.dao.UserResposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tangzhilong on 17/1/14.
 */
@Service
public class UserCache {
    @Autowired
    UserResposity userReposity;

    @Cacheable(value = "user", keyGenerator = "userKeyGenerator")
    public User findUser(Long userId) {
        return  userReposity.findOne(userId);
    }
}
