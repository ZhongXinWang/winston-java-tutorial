package com.winston.aop.test;

import com.winston.aop.logger.SimpleCalc;
import com.winston.spring.util.SpringContextUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 测试AOP
 * @date 2019/6/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springAop-applicationContext.xml"})
public class loggerAspectTest {

    @Autowired
    private ApplicationContext context;

    /**
     * 手动注入bean
     * @author  Winston.Wang
     * @date 2019/6/16
     * @param
     * @param
     * @return
     **/

    @Before
    public void before(){

        //创建bean的构造器
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SimpleCalc.class);
        //注册到context
        ConfigurableApplicationContext configContext = (ConfigurableApplicationContext) this.context;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configContext.getBeanFactory();
        //注册进去
        beanFactory.registerBeanDefinition("simpleCalc",beanDefinitionBuilder.getRawBeanDefinition());
    }

    /**
     * 测试通知
     * @author  Winston.Wang
     * @date 2019/6/16
     **/
    @Test
    public  void testLogger(){

        SimpleCalc calc = SpringContextUtils.getBean("simpleCalc",SimpleCalc.class);
        int result = calc.add(10,20);

        Assert.assertEquals(result,30);
    }

    /**
     * 测试异常通知
     * @author  Winston.Wang
     * @date 2019/6/17
     **/
    @Test
    public  void testReturnException(){


        SimpleCalc calc = SpringContextUtils.getBean("simpleCalc",SimpleCalc.class);
        int result = calc.div(10,0);
    }
}
