package com.winston.dao.impl;

import com.winston.dao.LiuHeDao;
import com.winston.vo.LiuHeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
* 六合彩数据服务层
* @author Winston.Wang
* @date 2019/8/18
* @version 1.0
**/
@Repository
public class LiuHeDaoImpl implements LiuHeDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public LiuHeVo getDataByYear(int year) {

        Query query = new Query();
        query.addCriteria(Criteria.where("year").is(year));
        List<LiuHeVo> liuHeVos = mongoTemplate.find(query, LiuHeVo.class);
        if(CollectionUtils.isEmpty(liuHeVos)){
            return  null;
        }

        return  liuHeVos.get(0);
    }

    @Override
    public List<LiuHeVo> getAllData() {

        Query query = new Query();
        List<LiuHeVo> liuHeVos = mongoTemplate.find(query, LiuHeVo.class);
        if(CollectionUtils.isEmpty(liuHeVos)){
            return  null;
        }
        return  liuHeVos;
    }
}
