package com.winston.service;

import com.winston.entity.User;

import java.util.List;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 用一句话描述我是干啥的
 * @date 2019/6/21
 */
public interface UserService {

    List<User> selectUser(User user);

    Long insert(User user);

    Long update(User user);
}
