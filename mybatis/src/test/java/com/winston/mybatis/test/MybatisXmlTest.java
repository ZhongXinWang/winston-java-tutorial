package com.winston.mybatis.test;

import com.winston.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 用一句话描述我是干啥的
 * @date 2019/6/23
 */
public class MybatisXmlTest {

    /**
     * 测试mybatis通过xml加载创建对象
     * @author  Winston.Wang
     * @date 2019/6/24
     **/
    @Test
    public  void testMybatisXml() throws IOException{

        Reader reader = Resources.getResourceAsReader("mybatis-config");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        List<User> list = sqlSession.selectList("com.winston.mapper.UserMapper.selectAllUser");
        list.stream().forEach(System.out::println);
    }
}
