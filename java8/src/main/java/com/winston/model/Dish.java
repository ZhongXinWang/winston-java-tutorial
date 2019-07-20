package com.winston.model;
/**
   菜单对象
 @Author Winston
 @date 2018年12月2日
 * 
 */
public class Dish {

	/**
	 * 菜名字
	 */
	private  String name;
	/**
	 * 是否是素菜
	 */
	private  boolean vegetarian;
	/**
	 * 卡路里
	 */
	private  int calories;
	/**
	 * 类型
	 */
	private  Type type;

	public enum Type { MEAT, FISH, OTHER }

	
	public Dish() {
		super();
	}

	public Dish(String name, boolean vegetarian, int calories, Type type) {
		super();
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Dish [name=" + name + ", vegetarian=" + vegetarian + ", calories=" + calories + ", type=" + type + "]";
	}

}
