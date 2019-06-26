package com.winston.aop.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个判断是否被锁定的注解
 * @author  Winston.Wang
 * @date 2019/6/19
 * @version 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {

    /**
     * 默认false，表示不锁定
     * @author  Winston.Wang
     * @date 2019/6/19
     * @param
     * @param
     * @return
     **/
    boolean isLocked() default  false;

}
