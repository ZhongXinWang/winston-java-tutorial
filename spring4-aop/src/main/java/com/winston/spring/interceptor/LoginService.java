package com.winston.spring.interceptor;

import com.winston.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 登录操作类
 * @date 2019/6/16
 */
@Service
public class LoginService {

    public User getUser(){

        User user = new User();
        user.setName("张三");
        user.setPassword("123456");
        return user;
    }
}
