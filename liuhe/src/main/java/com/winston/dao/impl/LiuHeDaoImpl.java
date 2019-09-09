package com.winston.service.impl;

import com.winston.service.LiuHeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
/**
* 六合彩数据服务层
* @author Winston.Wang
* @date 2019/8/18
* @version 1.0
**/
@Service
public class LiuHeServiceImpl implements LiuHeService {

    @Autowired
    private MongoTemplate mongoTemplate;



}
