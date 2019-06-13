package com.winston.simple.test;

import com.winston.entity.Animal;
import com.winston.spring.annotation.SpringAnnotaion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 用一句话描述我是干啥的
 * @date 2019/6/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-simple-example.xml"})
public class SpringXmlTest {

    @Autowired
    private ApplicationContext contextApplication;
    /**
     * 测试通过代码形式加载配置文件
     * @author  Winston.Wang
     * @date 2019/6/12
     **/
    @Test
    public  void testSpringXml(){

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-simple-example.xml");
        Animal aniaml = (Animal) context.getBean("animal");
        Assert.assertNotNull(aniaml);
    }

    /**
     * 测试通过ContextConfiguration加载xml
     * @author  Winston.Wang
     * @date 2019/6/13
     **/
    @Test
    public  void testSpringXml1(){

        Animal aniaml = (Animal) contextApplication.getBean("animal");
        Assert.assertNotNull(aniaml);
    }
}
