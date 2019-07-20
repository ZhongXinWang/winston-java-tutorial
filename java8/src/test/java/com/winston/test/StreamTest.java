package com.winston.test;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.winston.model.Dish;

/**
   测试类
 @Author Winston
 @date 2018年12月2日
 * 
 */
public class StreamTest {

	private List<Dish> menu;
	/**
	   初始化对象
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Before
	public void setBefore() {

	    List<Dish> tempMenu = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );
	    menu = new ArrayList<>(tempMenu);
	    
	}
	
	/**
	  测试数组转化为集合时抛出的异常
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testUnsupperException(){
		
		  List<Dish> tempMenu = Arrays.asList(
					new Dish("pork", false, 800, Dish.Type.MEAT),
					new Dish("beef", false, 700, Dish.Type.MEAT),
					new Dish("chicken", false, 400, Dish.Type.MEAT),
					new Dish("french fries", true, 530, Dish.Type.OTHER),
					new Dish("rice", true, 350, Dish.Type.OTHER),
					new Dish("season fruit", true, 120, Dish.Type.OTHER),
					new Dish("pizza", true, 550, Dish.Type.OTHER),
					new Dish("prawns", false, 300, Dish.Type.FISH),
					new Dish("salmon", false, 450, Dish.Type.FISH) );
		  //转化为List
		  List<Dish> menus = new ArrayList<>(tempMenu);
		  //打印结果
		  System.out.println(menus);
		  //清空结合
		  menus.clear();
	}
	
	/**
	 流的简单操作
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void test(){
		
		/**
		 * 筛选出卡路里大于300的前三条记录并取的名字
		 */
		List<String> result = menu.stream()    //从数据源获取Stream
				.filter(a->a.getCalories()>300)  //中间过程还是操作Stream   过滤出卡路里大于300
				.sorted(comparing(Dish::getCalories).reversed()) // 中间过程还是操作Stream 根据卡路里逆序
				.map(a->a.getName())  // 中间过程还是操作Stream 获取名字
				.limit(3)             // 中间过程还是操作Stream 取前三条
				.collect(Collectors.toList());  //终端  把结果输出到List
		System.out.println(result);
		
		/**
		 * 流只能操作一次之后就消失
		 */
		
		//统计鱼类型菜的个数
		long  count = menu.stream()
				.filter(a->a.getType()==Dish.Type.FISH)
				.count();
		System.out.println(count);
		
	}
	
	/**
	  流的筛选
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFilter(){
		
	
		/**
		 * 使用谓词筛选素菜,并调用内部迭代器输出
		 * Predicate 函数式接口下的test方法返回一个boolean方法
		 */
		menu.stream()
		.filter(Dish::isVegetarian)
		.collect(Collectors.toList())  //转化为List
		.forEach(System.out::println); //调用内部迭代输出
		
		/**
		 * 筛选不重复的元素,distinct
		 */
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream()
		.filter(i -> i % 2 == 0)  
		.distinct()   //去重
		.forEach(System.out::println); //调用内部迭代输出
		
		/**
		 * 截断流，取某几个元素
		 */
		numbers.stream()
		.filter(i -> i % 2 != 0)
		.limit(3)  //只取前面三个
		.forEach(System.out::println); //调用内部迭代输出
		
		/**
		 * 跳过元素，直接取后面的
		 */
		numbers.stream()
		.filter(i -> i % 2 != 0)
		.skip(2)    //跳过前两个
		.limit(3)   //值不够三个就不会往下取
		.forEach(System.out::println); //调用内部迭代输出
	}
	
	
	/**
	 map提取元素
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testMap(){
		
		/**
		 * 统计List下每个单词的长度
		 */
		List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
		
		List<Integer> wordLengths = words.stream()
		.map(String::length)
		.collect(Collectors.toList());
	
		for (Integer integer : wordLengths) {
			
			System.out.println(integer);
		}
		/**
		 * 统计返回的菜名长度
		 */
		List<Integer> result = menu.stream()    //从数据源获取Stream
				.filter(a->a.getCalories()>300)  //中间过程还是操作Stream   过滤出卡路里大于300
				.sorted(comparing(Dish::getCalories).reversed()) // 中间过程还是操作Stream 根据卡路里逆序
				.map(a->a.getName())  // 中间过程还是操作Stream 获取名字
				.map(String::length)
				.collect(Collectors.toList());  //终端  把结果输出到List
		for (Integer integer : result) {
			
			System.out.println(integer);
		}

	}
	
	/**
	 匹配和查找元素
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testSearch(){
		
		//判断是否有包含有匹配一个就返回true
		if(menu.stream().anyMatch(Dish::isVegetarian)){
			
			System.out.println("有素菜");
		}
		//匹配所有,都满足条件才返回true,在这边返回false
		if(menu.stream().allMatch(Dish::isVegetarian)){
			
			System.out.println("有些不是素菜");
		}
		
		//不匹配任何一个,一旦匹配上，返回false。在这边返回true，不存在
		if(menu.stream().map(Dish::getName).noneMatch(a->a.equals("pork1"))){
			
			System.out.println("pork1不存在");
		}
		
		//Optional类如果没有查找到元素不会返回空指针
		
		//查找元素,findAny获取任意元素
		Optional<Dish> dish =
				menu.stream()
				.filter(Dish::isVegetarian)
				.findAny();
		//如果存在值就输出
		dish.ifPresent(System.out::println);
		
		//查找并返回第一个元素
		Dish dish2 = menu.stream()
		 		.filter(Dish::isVegetarian)
				.findFirst().get();
		System.out.println(dish2);
	}
	
	/**
	 规约，使用reduce进行数据统计
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testReduce(){
		
		//统计总的卡路里
		Integer reduce = menu.stream()
				.map(Dish::getCalories)
				.reduce(0,(a,b)->a+b);
		System.out.println("总的卡路里："+reduce);
		
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
		
		menu.clear();
		menu = null;
	}
	
}
