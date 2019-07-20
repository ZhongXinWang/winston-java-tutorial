package com.winston.test;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.winston.model.Trader;
import com.winston.model.Transaction;

/**
   测试类交易练习
 @Author Winston
 @date 2018年12月2日
 * 
 */
public class TraderTest {

	private List<Transaction> transactions;
	/**
	   初始化对象
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Before
	public void setBefore() {
		
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		List<Transaction> temp = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950)
				);
		transactions=new ArrayList<>(temp);
	}
	/**
	  找出2011发生的所有交易，并按交易额排序（从低到高）
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFindTransaction(){
		
		transactions.stream().filter((Transaction t)->t.getYear()==2011) //筛选出2011年的
							 .sorted(comparing(Transaction::getValue))   //按照交易值排序
							 .forEach(System.out::println);   //输出
		/**
		 * out:
		 * Transaction [trader=Trader [name=Brian, city=Cambridge], year=2011, value=300]
		   Transaction [trader=Trader [name=Raoul, city=Cambridge], year=2011, value=400]
		 */
	}
	
	/**
	  交易员都在哪些不同的ۡ城市工作过？ 
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testDifferentCity(){
		
		/**
		 * 写法一
		 */
		transactions.stream().map(Transaction::getTrader) //提取所有的交易员
							 .map((a)->a.getCity())     //从交易员中提取城市
							 .distinct()  //去重
							 .forEach(System.out::println);//输出
		
		/**
		 * 写法二
		 */
		transactions.stream().map((Transaction t)->t.getTrader().getCity())//提取所有的交易员
							 .collect(Collectors.toSet()); //转化为set
		
		/**
		 * out:
		 * Cambridge
		   Milan
		 * 
		 */
	}
	
	/**
	   查找所有来自于剑桥的交易员，并按姓名排序
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFindCambridgeTrader(){
		
		transactions.stream().map(Transaction::getTrader)  //提取所有的交易员
							 .filter((a)->a.getCity().equals("Cambridge"))//来自剑桥
							 .sorted(comparing(Trader::getName).reversed()) //根据姓名逆序
							 .distinct()  //去重
							 .forEach(System.out::println);//输出
		/**
		 * out:
		  Trader [name=Raoul, city=Cambridge]
		  Trader [name=Brian, city=Cambridge]
		  Trader [name=Alan, city=Cambridge]
		 * 
		 */
	}
	
	/**
	   返回所有交易员的姓名字符串，按字母顺序排序。
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testFindTrader(){
		
		String collect = transactions.stream().map(t->t.getTrader().getName())  //提取所有的交易员
							 .sorted() //根据姓名排序
							 .distinct()  //去重
							 .collect(Collectors.joining());//拼接名字
		System.out.println(collect);
		/**
		 * out:
	            AlanBrianMarioRaoul
		 * 
		 */
	}
	
	/**
	   有没有交易员是在米兰工作的
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testMatchTraderCity(){
		
		boolean anyMatch = transactions.stream().map(Transaction::getTrader)  //提取所有的交易员
							 .anyMatch((t)->t.getCity().equals("Milan"));//有没有任意一个交易员在米兰工作
		System.out.println(anyMatch);
		/**
		 * out:
		 * true
		 * 
		 */
	}
	
	/**
	   打印生活在剑桥的交易员的所有交易额并求和。
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testReduceTraderMoney(){
		
		/**
		 * 写法一，这种写法Integer必须被拆箱才能被计算
		 */
		Integer sum = transactions.stream()
								  .filter((t)->t.getTrader().getCity().equals("Cambridge"))
								  .map(Transaction::getValue)
								  .reduce(0, Integer::sum);

		System.out.println(sum);
		
		/**
		 * 使用原始类型流,避免装箱拆箱开销
		 * 
		 * mapToInt  把提取的值转化为IntStream
		 * mapToDouble  把提取的值转化为DoubleStream
		 * mapToLong  把提取的值转化为LongStream
		 * 操作方法：max(),min()，sum(),average()
		 */
		int sum2 = transactions.stream().filter((t) -> t.getTrader().getCity().equals("Cambridge"))
							 .mapToInt(Transaction::getValue)
							 .sum();
		
		/**
		 * out:
		 * 2650
		 * 
		 */
	}
	

	/**
	   所有交易中，最高的交易额是多少？
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testGetMax(){
		
		/**
		 * 写法一
		 * 使用排序
		 */
		
				Transaction transaction = transactions.stream()
								     .max(comparing(Transaction::getValue))
									 .get();
		/**
		 * 写法二
		 * 使用reduce
		 */
				
				Optional<Integer> highestValue =transactions.stream()
									.map(Transaction::getValue)
									.reduce(Integer::max);	
		/**
		 * 写法三
		 * 使用OptionalInt，在没有找到最大值或是最小值的时候，可以设置默认值,避免出现空
		 */
				OptionalInt max = transactions.stream()
							.mapToInt(Transaction::getValue)
							.max();
				//没有最大值，可以设置一个默认值
				int orElse = max.orElse(100);

		System.out.println(transaction.getValue());
		
		
		/**
		 * out:
		 * 1000
		 * 
		 */
	}
	
	/**
	   找到交易额最小的交易
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testGetMin(){
		
		
				Transaction transaction = transactions.stream()
								     .max(comparing(Transaction::getValue).reversed())
									 .get();
		System.out.println(transaction.getValue());
		/**
		 * out:
		 * 300
		 * 
		 */
	}
	
	/**
	  求一定范围内勾股数(3,4,5)
	 @Author Winston
	 @email 940945444@qq.com
	 @return
	 @param
	 */
	@Test
	public void testCongruent(){
		
		Stream<double[]> pythagoreanTriples2 =
				IntStream.rangeClosed(1, 100)//产生[1,100]随机数
				.boxed()  //IntStream转化为普通Stream
				.flatMap(a ->IntStream.rangeClosed(a, 100)
				.mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)}) //构造三元数
				.filter(t -> t[2] % 1 == 0));  //Math.sqrt(a*a + b*b)会是整数,筛选符合的元素
		//只输出5条
		pythagoreanTriples2.limit(5).forEach(t->System.out.println(t[0]+","+t[1]+","+t[2]));
		
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
		
		transactions.clear();
		transactions = null;
	}
	
}
