package com.winston.aop.test;

import com.winston.aop.annotation.SecuredAspect;
import com.winston.aop.annotation.SecuredMethod;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 使用注解织人代码
 * @date 2019/6/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SecuredMethod.class, SecuredAspect.class})
public class SecuredAspectTest {

    @Autowired
    private SecuredMethod securedMethod;
    /**
     * 测试啥呢
     * @author  Winston.Wang
     * @date 2019/6/19
     **/
    @Test
    public  void test(){

        /**
         * 要想使用注解来实现Aspect代理类，
         * 把该类注册到AOP代理中，没有实现接口，这边使用的是cglib代理,  CglibAopProxy
         *  @EnableAspectJAutoProxy开启直接支持
         *  xml中，使用<aop:aspectj-autoproxy  proxy-target-class="true"/>   true：使用cglib代理，false使用jdk代理。不配
         *  根据是否实现接口匹配
         */

        securedMethod.lockMethod();

        securedMethod.unLockMethod();
    }
}
