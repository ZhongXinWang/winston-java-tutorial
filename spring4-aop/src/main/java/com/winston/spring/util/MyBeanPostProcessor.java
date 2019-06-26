package com.winston.spring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 在bean初始化之前或初始化之后做一下操作
 * @date 2019/6/16
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    private Logger logger = LoggerFactory.getLogger(MyBeanPostProcessor.class);

    /**
     * 初始化之前
     * @author  Winston.Wang
     * @date 2019/6/16
     * @param
     * @param
     * @return
     **/

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        logger.info(beanName+"----------------------初始化之前----------------------------");

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        logger.info(beanName+"----------------------初始化之后----------------------------");

        return bean;
    }
}
