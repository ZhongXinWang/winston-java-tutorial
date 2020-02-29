package com.winston.service.test;

import com.winston.application.Application;
import com.winston.service.LiuHeService;
import com.winston.vo.LiuHeVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class LiuHeServiceTest {

    @Autowired
    private LiuHeService liuHeService;
    /**
    * 根据年获取数据
    * @author Winston.Wang
    * @date 2019/8/24
    * @param
    * @param
    * @return
    **/
    @Test
    public  void testGetDataByYear(){

        int year = 1976;

        LiuHeVo liuHeVo = liuHeService.getDataByYear(year);

        Assert.assertNotNull(liuHeVo);
    }

    /**
     * 测试获取所有数据
     */
    @Test
    public  void testGetAllData(){

        List<Map<Integer, List<Integer>>> allData = liuHeService.getAllData();
        allData.stream().forEach(a->{
            Integer key = a.keySet().iterator().next();

            System.out.println(key+":"+a.get(key));
        });
    }

    /**
     * 测试java8统计数据
     */
    @Test
    public  void testJava8Count(){

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(4);
        list.add(4);
        list.add(2);
        Map<Integer, Long> collect = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> sortMap = new LinkedHashMap<>();
        //由于hashmap无序，所以在排序放入LinkedHashMap里(value升序)
        collect.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                forEach(e -> sortMap.put(e.getKey()+"", e.getValue()));

        System.out.println(sortMap);
        //获取排序后map的key集合
        List<String> keys = new LinkedList<>();
        sortMap.entrySet().stream().limit(2).forEach(a->keys.add(a.getKey()));
        System.out.println(keys);

        //获取排序后map的value集合
        List<Long> values = new LinkedList<>();
        sortMap.entrySet().stream().limit(2).forEachOrdered(e -> values.add(e.getValue()));
        System.out.println(values);

    }

    /**
     * 获取所有数据中号码出现的次数
     */
    @Test
    public  void testGetAnalyCountData(){

        //获取排名前10名
        Map<String, List<Integer>> analyCountData = liuHeService.getAnalyCountData(20);

        /**
         * [10, 6, 14, 34, 27, 5, 19, 35, 7, 30]
         * [149, 134, 134, 131, 129, 128, 127, 125, 124, 124]
         */
        System.out.println("A------------------");
        System.out.println(analyCountData.get("AK"));
        System.out.println(analyCountData.get("AV"));

        System.out.println("T1------------------");
        System.out.println(analyCountData.get("TK"));
        System.out.println(analyCountData.get("TV"));


        System.out.println("P1------------------");
        System.out.println(analyCountData.get("P1K"));
        System.out.println(analyCountData.get("P1V"));


        System.out.println("P2------------------");
        System.out.println(analyCountData.get("P2K"));
        System.out.println(analyCountData.get("P2V"));

        System.out.println("P3------------------");
        System.out.println(analyCountData.get("P3K"));
        System.out.println(analyCountData.get("P3V"));


        System.out.println("P4------------------");
        System.out.println(analyCountData.get("P4K"));
        System.out.println(analyCountData.get("P4V"));


        System.out.println("P5------------------");
        System.out.println(analyCountData.get("P5K"));
        System.out.println(analyCountData.get("P5V"));


        System.out.println("P6------------------");
        System.out.println(analyCountData.get("P6K"));
        System.out.println(analyCountData.get("P6V"));

    }


}
