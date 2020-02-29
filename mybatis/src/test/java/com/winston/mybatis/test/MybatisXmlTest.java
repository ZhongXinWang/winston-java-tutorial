package com.winston.mybatis.test;

import com.winston.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

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

        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        List<User> list = sqlSession.selectList("com.winston.mapper.UserMapper.selectAllUser");
        list.stream().forEach(System.out::println);
    }

    /**
     * 加載properties文件
     * @author  Winston.Wang
     * @date 2019/6/26
     **/
    @Test
    public  void testProperties(){

        ClassLoader loader = MybatisXmlTest.class.getClassLoader();
        InputStream resourceAsStream = loader.getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            String driver = properties.getProperty("driver");
            Assert.assertEquals(driver,"com.mysql.jdbc.Driver");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
