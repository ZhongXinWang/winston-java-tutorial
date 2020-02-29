package com.winston.dao.test;

import com.winston.app.Application;
import com.winston.dao.CommonDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class CommonDaoTest {

    @Resource
    private CommonDao commonDao;

    /**
    * 测试插入
    * @author Winston.Wang
    * @date 2019/7/24
    * @version 1.0
    **/
    @Test
    public  void testInsert(){

        String sql = "insert into test(name,age) values(?,?);";
        int count = commonDao.insert(sql,"lisi",20);
        Assert.assertEquals(count,1);
    }

    /**
    * 测试更新
    * @author Winston.Wang
    * @date 2019/7/24
    * @version 1.0
    **/
    @Test
    public  void testUpdate(){


    }

}
