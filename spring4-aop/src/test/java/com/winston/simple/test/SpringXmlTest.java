package com.winston.simple.test;

import com.winston.entity.Animal;
import com.winston.spring.annotation.SpringAnnotaion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
@ContextConfiguration(locations = {"classpath:spring-simple-example.xml"})
public class SpringXmlTest {

    @Autowired
    private ApplicationContext configContext;
    /**
     * 使用代码加载xml
     * @author  Winston.Wang
     * @date 2019/6/12
     **/
    @Test
    public  void testSpringXml(){

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-simple-example.xml");
        Animal animal = (Animal) context.getBean("animal");
        Assert.assertNotNull(animal);
    }

    /**
     * 使用单元测试加载xml
     * @author  Winston.Wang
     * @date 2019/6/15
     **/
    @Test
    public  void testSpringConfigXml(){

        Animal animal = (Animal) configContext.getBean("animal");
        Assert.assertNotNull(animal);
    }
}
