package com.tzl.controller;

import com.tzl.bean.User;
import com.tzl.dao.UserResposity;
import com.tzl.services.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hztangzhilong on 2016/12/2.
 */
@Controller
public class HelloWorldController {
    @Autowired
    UserResposity userReposity;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    UserCache userCache;

    @RequestMapping("/users")
    @ResponseBody
    Iterable<User> users() {
        return userReposity.findAll();
    }

    @RequestMapping("/addUser")
    String addUser(@RequestParam("userName") String userName,@RequestParam("pwd") String pwd) {
        User user1 = new User();
        user1.setUserName(userName);
        user1.setPassword(pwd);
        userReposity.save(user1);
        return "redirect:/";
    }

    @RequestMapping("/findUser")
    @ResponseBody
     User findUser(@RequestParam("userId") Long userId) {
       return  userCache.findUser(userId);
    }
}
