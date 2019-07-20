package com.winston.model;
/**
  交易实体
 @Author Winston
 @date 2018年12月4日
 * 
 */
public class Transaction {

	/**
	 * 交易员
	 */
	private  Trader trader;
	/**
	 * 年份
	 */
	private  int year;
	/**
	 * 金额
	 */
	private  int value;
	
	public Transaction(Trader trader, int year, int value) {
		super();
		this.trader = trader;
		this.year = year;
		this.value = value;
	}
	public Trader getTrader() {
		return trader;
	}
	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Transaction [trader=" + trader + ", year=" + year + ", value=" + value + "]";
	}

}
