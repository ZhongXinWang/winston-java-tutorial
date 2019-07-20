package com.winston.interfac.impl;

import com.winston.interfac.ApplePredicate;
import com.winston.model.Apple;

/**
  获取绿色的苹果实现类
 @Author Winston
 @date 2018年12月1日
 * 
 */
public class AppleColorPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple,String args) {

		return args.equals(apple.getColor());
	}
}
