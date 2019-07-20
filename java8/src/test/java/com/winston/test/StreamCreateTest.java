package com.winston.test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
   测试类
 @Author Winston
 @date 2018年12月2日
 * 
 */
public class StreamCreateTest {

	/**
	   初始化对象
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Before
	public void setBefore() {
 
	}
	
	/**
	  由值创建流
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testCreateStreamByValue(){
		
		/**
		 * Stream 接口提供了一个static of(T ...args)可变参数的方法
		 * 里面调用Arrays.stream(values);创建一个流
		 */
		
		Stream<String> stream = Stream.of("Hello","World","java","python");
		stream.map(String::toUpperCase).forEach(System.out::println);
	}
	
	/**
	 由数组创建流
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testCreateStreamByArray(){
		
		//可以根据不同类型创建不同的流  IntStream,DoubleStream,LongStream
		int[] values = {1,23,4,5,6,76,7,7,8};
		
		IntStream stream = Arrays.stream(values);
		System.out.println(stream.sum());
	}
	/**
	  由文件创建流
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testCreateStreamByFile(){
		
		
		try(Stream<String> lines = Files.lines(Paths.get("data.txt"),Charset.defaultCharset())){
			
			lines.forEach(System.out::println);
			
			
		}catch(Exception e){
			
			e.printStackTrace();
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
	}
	
}
