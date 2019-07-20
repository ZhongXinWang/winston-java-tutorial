package com.winston.test;

import com.winston.interfac.ApplePredicate;
import com.winston.interfac.impl.AppleColorPredicate;
import com.winston.interfac.impl.AppleWeightPredicate;
import com.winston.main.Main;
import com.winston.model.Apple;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

/**
 @Author Winston
 @date 2018年11月29日
   测试类 
 */
public class FirstTest{

	/*
	 * 苹果列表
	 */
	private List<Apple> inventory;
	/**
	   初始化对象
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Before
	public void setBefore() {

		inventory = new ArrayList<>();
		inventory.add(new Apple("red",190));
		inventory.add(new Apple("green",100));
		inventory.add(new Apple("green",190));
		inventory.add(new Apple("green",190));
		inventory.add(new Apple("red",190));
	}
	
	/**
	 *你可能想要选出所有的绿苹果，并返回一个列表。
	 *通常我们用ኘᤤ （ filter）一词来表达这个概念 
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFilterGreenApple(){
		
		List<Apple> result = Main.filterGreenApple(inventory);
		Assert.assertEquals(result.size(), 3);
	}
	
	/**
	有人可能想要选出重的苹果，比如超过150克，于是你心情ෛ重地写了下面这
	个方法
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFilterWeightApple(){
		
		List<Apple> result = Main.filterGreenApple(inventory);
		Assert.assertEquals(result.size(), 4);
	}
	
	/**
	 *使用策略模式
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFilterApple1(){
		//获取颜色为绿色的苹果
		List<Apple> resultGreen =Main.filterApple(inventory,new AppleColorPredicate(),"green");
		Assert.assertEquals(resultGreen.size(), 3);
		
		//获取重量大于某个值的苹果
		List<Apple> resultWeight =Main.filterApple(inventory,new AppleWeightPredicate(),"180");
		Assert.assertEquals(resultWeight.size(), 4);
	}
	
	/**
	 *使用匿名类
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFilterAppleNoName(){
		//获取颜色为绿色的苹果
		List<Apple> resultGreen =Main.filterApple(inventory,new ApplePredicate() {
			
			@Override
			public boolean test(Apple apple, String args) {

				return args.equals(apple.getColor());
			}
		},"green");
		Assert.assertEquals(resultGreen.size(), 3);
		
		//获取重量大于某个值的苹果
		List<Apple> resultWeight =Main.filterApple(inventory,new ApplePredicate() {
			
			@Override
			public boolean test(Apple apple, String args) {

				return apple.getWeight() > Integer.parseInt(args);
			}
		},"180");
		Assert.assertEquals(resultWeight.size(), 4);
	}
	
	
	/**
	 *java.util.function  Predicate 使用java8优化代码 
	 *使用传递方法的方式，提高灵活性
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFilterApple(){
		//获取颜色为绿色的苹果
		List<Apple> resultGreen =Main.filterApple(inventory, Apple::isGreenApple);
		Assert.assertEquals(resultGreen.size(), 3);
		
		//获取重量大于某个值的苹果
		List<Apple> resultWeight =Main.filterApple(inventory, Apple::isHeavyApple);
		Assert.assertEquals(resultWeight.size(), 4);

		//重量大于某个值的苹果并且是绿色的
		List<Apple> resultGreenAndWeight =Main.filterApple(inventory, Apple::isGreenAndHeavyApple);
		Assert.assertEquals(resultGreenAndWeight.size(), 2);
	}
	
	/**
	 *java.util.function  Predicate 使用java8优化代码 
	 *使用传递方法的方式，提高灵活性
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFilterAppleUseLambda(){
		//获取颜色为绿色的苹果
		List<Apple> resultGreen =Main.filterApple(inventory,(Apple a) -> "green".equals(a.getColor()));
		Assert.assertEquals(resultGreen.size(), 3);
	
		//获取重量大于某个值的苹果
		List<Apple> resultWeight =Main.filterApple(inventory, (Apple a) ->a.getWeight()>180);
		Assert.assertEquals(resultWeight.size(), 4);

		//重量大于某个值的苹果并且是绿色的
		List<Apple> resultGreenAndWeight =Main.filterApple(inventory, (Apple a) ->a.getWeight()>180 && "green".equals(a.getColor()));
		Assert.assertEquals(resultGreenAndWeight.size(), 2);
	}
	
	/**
	 *java.util.function  Predicate 使用java8优化代码 
	 *使用传递方法的方式，提高灵活性
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testCommonFilterLambda(){
		//获取颜色为绿色的苹果
		List<Apple> resultGreen =Main.filter(inventory,(Apple a) -> "green".equals(a.getColor()));
		Assert.assertEquals(resultGreen.size(), 3);
	
		//获取重量大于某个值的苹果
		List<Apple> resultWeight =Main.filter(inventory, (Apple a) ->a.getWeight()>180);
		Assert.assertEquals(resultWeight.size(), 4);

		//重量大于某个值的苹果并且是绿色的
		List<Apple> resultGreenAndWeight =Main.filter(inventory, (Apple a) ->a.getWeight()>180 && "green".equals(a.getColor()));
		Assert.assertEquals(resultGreenAndWeight.size(), 2);
	}
	
	/**
	 *排序
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testSort(){
		
		//排序前
		for (Apple apple : inventory) {
			
			System.out.println(apple.toString());
		}
		
		//排序，使用lambda表达式
		inventory.sort(new Comparator<Apple>() {

			@Override
			public int compare(Apple o1, Apple o2) {

				if(o1.getWeight() > o2.getWeight()){
					
					return -1;
				}
				
				if(o1.getWeight() == o2.getWeight() ){
					
					return 0;
				}
				
				return 1;
				
			}
		});
		
		System.out.println("-----------------");
		ListIterator<Apple> it = inventory.listIterator();
		while(it.hasNext()){
			
			System.out.println(it.next());
		}
		
		Thread t = new Thread(() -> System.out.println("Hello world"));
		t.start();
		
	}
	
	
	/**
	  程序结束,释放对象
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@After
	public void setAfter(){
		
		inventory.clear();
		inventory = null;
	}

}
