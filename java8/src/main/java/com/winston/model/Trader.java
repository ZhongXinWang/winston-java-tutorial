package com.winston.model;
/**
   交易员实体
 @Author Winston
 @date 2018年12月4日
 * 
 */
public class Trader {

	/**
	 * 交易员名称
	 */
	private  String name;
	/**
	 * 交易员所在的城市
	 */
	private  String city;

	public Trader(String name, String city) {
		super();
		this.name = name;
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Trader [name=" + name + ", city=" + city + "]";
	}

}
