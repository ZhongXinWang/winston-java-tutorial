package com.winston.dao;

/**
* dao操作接口
* @author Winston.Wang
* @date 2019/7/24
* @version 1.0
**/
public interface CommonDao {

    int insert(String sql,Object... args);

    int update(String sql,Object... args);
}
