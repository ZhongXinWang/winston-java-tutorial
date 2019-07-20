package com.winston.interfac;

import com.winston.model.Apple;

/**
   苹果筛选的接口
 @Author Winston
 @date 2018年12月1日
 * 
 */
public interface ApplePredicate {

	boolean test(Apple apple,String args);
}
