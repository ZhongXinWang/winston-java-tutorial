package com.winston.interfac.impl;

import com.winston.interfac.ApplePredicate;
import com.winston.model.Apple;

/**
 筛选重量
 @Author Winston
 @date 2018年12月1日
 * 
 */
public class AppleWeightPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple,String args) {

		return apple.getWeight() > Integer.parseInt(args);
	}
}
