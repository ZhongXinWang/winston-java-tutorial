package com.winston.main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.winston.interfac.ApplePredicate;
import com.winston.model.Apple;

/**
 @Author Winston
 @date 2018年11月29日
 * 
 */
public class Main {
	
	
	/**
	 *获取颜色为绿色的苹果
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	public static List<Apple> filterGreenApple(List<Apple> inventory){

		List<Apple> result = new ArrayList<>();
		for (Apple item : inventory) {

			if ("green".equals(item.getColor())) {

				result.add(item);
			}

		}
		return result;
	}
	
	/**
	 *获取重量大于180的苹果
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	public static List<Apple> filterWeightApple(List<Apple> inventory){

		List<Apple> result = new ArrayList<>();
		for (Apple item : inventory) {

			if (item.getWeight() > 180) {

				result.add(item);
			}
		}
		return result;
	}
	
	/**
	 *通过参数封装判断
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	public static List<Apple> filterWeightOrGreenApple(List<Apple> inventory,String color,double weight,boolean flag){
		//flag=true 筛选重量
		//flag=false 筛选颜色
		List<Apple> result = new ArrayList<>();
		for (Apple item : inventory) {

			if ((flag && item.getWeight() > weight) || (!flag && color.equals(item.getWeight())) ) {

				result.add(item);
			}
		}
		return result;
	}
	
	/**
	 *通过策略设计模式方式
	 @Author Winston
	 @email 940945444@qq.com
	 @return args
	 @param inventory
	 */
	public static List<Apple> filterApple(List<Apple> inventory,ApplePredicate p,String args){
		
		List<Apple> result = new ArrayList<>();
		for (Apple item : inventory) {

			if (p.test(item, args)) {

				result.add(item);
			}
		}
		return result;
	}
	
	/**
	 *java.util.function  Predicate 使用java8优化代码 
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	public static List<Apple> filterApple(List<Apple> inventory,Predicate<Apple> p){

		List<Apple> result = new ArrayList<>();
		for (Apple item : inventory) {
			
			if(p.test(item)){
				
				result.add(item);
			}
			
		}
		return result;
	}
	/**
	 *通用的filter 
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	public static <T> List<T> filter(List<T> inventory,Predicate<T> p){
		
		List<T> result = new ArrayList<T>();
		for (T t : inventory) {
			
			if(p.test(t)){
				
				result.add(t);
			}
			
		}
		
		return  result;
	}

	

}
