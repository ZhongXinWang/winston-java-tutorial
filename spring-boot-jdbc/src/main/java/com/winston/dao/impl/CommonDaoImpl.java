package com.winston.dao.impl;

import com.winston.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDaoImpl implements CommonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(String sql, Object... args) {

       return jdbcTemplate.update(sql,args);

    }

    @Override
    public int update(String sql, Object... args) {

        return jdbcTemplate.update(sql,args);
    }
}
