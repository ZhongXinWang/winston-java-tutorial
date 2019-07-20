package com.winston.model;

import java.util.List;

/**
 @Author Winston
 @date 2018年11月29日
 *苹果对象 
 */
public class Apple {

	/*
	 * 苹果的颜色
	 */
	private String color;
	/*
	 * 苹果重量
	 */
	private double weight;

	public int big;
	
	
	
	public int getBig() {
		return big;
	}

	public void setBig(int big) {
		this.big = big;
	}

	public Apple(String color) {
		super();
		this.color = color;
	}
	
	public Apple(String color, double weight) {
		super();
		this.color = color;
		this.weight = weight;
	}


	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	/**
	 * 获取绿色的方法
	 * 
	 * @Author Winston
	 * @email 940945444@qq.com
	 * @return
	 * @param
	 */
	public static boolean isGreenApple(Apple apple) {
		return "green".equals(apple.getColor());
	}

	/**
	 *获取重量大于150的方法 
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	public static boolean isHeavyApple(Apple apple) {
		return apple.getWeight() > 180;
	}
	
	/**
	 *获取重量大于150并且时绿色的方法
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	public static boolean isGreenAndHeavyApple(Apple apple) {
		return apple.getWeight() > 180 && "green".equals(apple.getColor()) ;
	}

	@Override
	public String toString() {
		return "Apple [color=" + color + ", weight=" + weight + "]";
	}
	
	
}
