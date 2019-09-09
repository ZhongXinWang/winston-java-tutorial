package com.winston.dao;

public interface UserDao {

    int insert(String sql,Object... args);

    int update(String sql,Object... args);
}
