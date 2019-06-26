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
@ContextConfiguration(classes = SpringAnnotaion.class,loader=AnnotationConfigContextLoader.class)
public class SpringAnnotationTest {

    @Autowired
    private Animal animalMethod;
    /**
     * 测试ContextConfiguration加载注解类，注入Bean
     * @author  Winston.Wang
     * @date 2019/6/12
     **/
    @Test
    public  void testSpringAnnotation(){

        Assert.assertNotNull(animalMethod);
    }
}
