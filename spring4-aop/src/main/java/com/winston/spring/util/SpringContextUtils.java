package com.winston.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: spring 工具类,这个类如果要使用，要在spring配置加载这个类
 * @date 2019/6/16
 */
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        context = applicationContext;
    }

    public ApplicationContext getContext(){

        return context;
    }

    /**
     * 获取bean
     * @author  Winston.Wang
     * @date 2019/6/16
     * @param
     * @param
     * @return
     **/
    public  static Object getBean(String beanName) throws  BeansException{

        return context.getBean(beanName);
    }

    /**
     * 获取指定类型的Bean
     * @author  Winston.Wang
     * @date 2019/6/16
     * @param
     * @param
     * @return
     **/

    public static <T> T getBean(String beanName,Class<T> classType){

        return context.getBean(beanName,classType);
    }
}
