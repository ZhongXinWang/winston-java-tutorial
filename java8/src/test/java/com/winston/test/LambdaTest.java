package com.winston.test;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.winston.main.Main;
import com.winston.model.Apple;

/**
 @Author Winston
 @date 2018年11月29日
   测试类 
 */
public class LambdaTest{

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
		inventory.add(new Apple("red",-1));
		inventory.add(new Apple("green",100));
		inventory.add(new Apple("green",100));
		inventory.add(new Apple("green",90));
		inventory.add(new Apple("red",180));
	}
	
	/**
	 lambda 表达式调用排序
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFirst(){
		
		//使用lambda计算乘
		System.out.println(add((int a,int b)->{return a*b;},4,5));
		//使用lambda计算除
		System.out.println(add((int a,int b)->{return a/b;},4,5));
		//使用lambda计算加
		System.out.println(add((int a,int b)->{return a+b;},4,5));
		//使用lambda计算-
		System.out.println(add((int a,int b)->{return a-b;},4,5));
		
		
		//使用lambda计算乘
		Calc c = (int a,int b)->{return a*b;};
		System.out.println(c.calc(100, 200));
		//使用lambda计算加
		Calc c1 = (int a,int b)->a+b;
		System.out.println(c1.calc(100, 200));
		
		
	}
	/**
	 *内置的函数式接口 
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFunction(){
		
		//Function提供的函数式接口Predicate，方法式test()  返回boolean
		Predicate<String> p = (String x)->"hello".equals(x);
		System.out.println(p.test("hello")); //out=true

		//Function提供的函数式接口Consumer，方法式accept()  返回void
		Consumer<String> c = (String x) -> System.out.println(x+"World");
		c.accept("Hello"); //out=HelloWorld
		
		// @param <T> the type of the input to the function
		// @param <R> the type of the result of the function
		Function<String, Integer> f = (String s)->s.length();
		System.out.println(f.apply("hello"));   //out=5
		/*
		IntPredicate,    针对int基本数据类型Predicate
		LongPredicate,   针对long基本数据类型Predicate
		DoublePredicate  针对double基本数据类型Predicate
		Consumer<T> T->void 
		IntConsumer, 针对int基本数据类型Consumer
		LongConsumer, 针对long基本数据类型Consumer
		DoubleConsumer 针对double基本数据类型Consumer
		Function<T,R> T->R IntFunction<R>,
		IntToDoubleFunction,  输入int转化为double
		IntToLongFunction,
		LongFunction<R>,
		LongToDoubleFunction,
		LongToIntFunction,
		DoubleFunction<R>,
		ToIntFunction<T>,
		ToDoubleFunction<T>,
		ToLongFunction<T>*/
		Comparator<Apple> com = (Apple a,Apple b) -> a.getWeight()-b.getWeight()>0?-1:1;
		inventory.sort(com);
		for (Apple apple : inventory) {
			
			System.out.println(apple);
		}
	}
	static int add(Calc c,int a,int b){
		
		return c.calc(a, b);
	}
	
	
	/**
	  方法引用，lambda的简写
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testMethod(){

		String str = "hello";
		Function<String,Integer> s = String::length;
		Integer apply = s.apply(str);
		System.out.println(apply);
		
		//lambda方式，调用Comparator接口的静态方法进行排序
		inventory.sort(Comparator.comparing((Apple a)->a.getWeight()));
		//方法引用方式
		inventory.sort(comparing(Apple::getWeight));
		//逆序reversed()
		inventory.sort(comparing(Apple::getWeight).reversed());
		//按重量排序重量相同按颜色
		inventory.sort(comparing(Apple::getWeight).thenComparing(Apple::getColor));
		for (Apple apple : inventory) {
			
			System.out.println(apple);
		}
		
	}
	
	/**
	   谓词组合
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testPredicateNegate(){
		
		//获取重量小于100的苹果
		Predicate<Apple> noWeight = (Apple a)->a.getWeight()<=90;
		//取非，获取重量大于100的苹果，a.getWeight()>=90,and绿色的苹果
		Predicate<Apple> noWeightAndGreen = noWeight.negate().and((Apple a)->"green".equals(a.getColor()));
		
		List<Apple> result = Main.filter(inventory, noWeightAndGreen);
		
		for (Apple apple : result) {
			
			System.out.println(apple);
		}
		
		//取颜色为绿色或是重量大于150
		Predicate<Apple> greenOrWeight = (Apple a)->"green".equals(a.getColor());
		//添加or谓词
		List<Apple> result1 = Main.filter(inventory,greenOrWeight.or((a)->a.getWeight()>150));
		System.out.println("取颜色为绿色或是重量大于150");
		for (Apple apple : result1) {
			
			System.out.println(apple);
		}
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
/**
 * 定义函数式接口，一定要含有至少一个抽象方法
 * @author wzx
 *
 */
@FunctionalInterface
 interface Calc{
	
	int calc(int a,int b);
}
