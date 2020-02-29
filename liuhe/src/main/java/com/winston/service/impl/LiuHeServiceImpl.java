package com.winston.service.impl;

import com.winston.dao.LiuHeDao;
import com.winston.service.LiuHeService;
import com.winston.vo.LiuHeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* 六合彩数据服务层
* @author Winston.Wang
* @date 2019/8/18
* @version 1.0
**/
@Service
public class LiuHeServiceImpl implements LiuHeService {

    @Autowired
    private LiuHeDao liuHeDao;

    /**
     * 根据年获取
     * @param year
     * @return
     */
    @Override
    public LiuHeVo getDataByYear(int year) {

        return liuHeDao.getDataByYear(year);
    }

    /**
     * 获取按年分组的数据
     * @return
     */
    @Override
    public List<Map<Integer,List<Integer>>> getAllData() {

        List<LiuHeVo> allData = liuHeDao.getAllData();
        List<Map<Integer,List<Integer>>> maps = new ArrayList<>();
        allData.stream().forEach(a->{
            Map<Integer, List<Integer>> map = new HashMap<>();
            List<Integer>  data = new ArrayList<>();
            a.getData().stream().forEach(item->{
                data.add(Integer.parseInt(item.getT()));
                data.add(Integer.parseInt(item.getP1()));
                data.add(Integer.parseInt(item.getP2()));
                data.add(Integer.parseInt(item.getP3()));
                data.add(Integer.parseInt(item.getP4()));
                data.add(Integer.parseInt(item.getP5()));
                data.add(Integer.parseInt(item.getP6()));
            });
            map.put(a.getYear(),data);
            maps.add(map);
        });
        return maps;
    }

    /**
     * 获取所有记录
     * @return
     */
    @Override
    public List<LiuHeVo> getData() {

        List<LiuHeVo> allData = liuHeDao.getAllData();

        return allData;
    }

    /**
     * 获取出现次数最多数字的排名
     * return  key=TK/TV   value=数字集合/次数集合
     * limit  限制排名
     */
    @Override
    public Map<String,List<Integer>>  getAnalyCountData(int limit){

        //获取值
        List<LiuHeVo> allData = liuHeDao.getAllData();
        return  getCountData(allData,limit);
    }

    /**
     * 根据传入的集合，获取出现次数最多数字的排名
     * limit  限制排名
     * return  key=TK/TV   value=数字集合/次数集合
     */
   private Map<String,List<Integer>>  getCountData(List<LiuHeVo> allData,int limit){

        Map<String,List<Integer>> map = new HashMap<>();
        //存储对应的数组
        List<Integer> t = new ArrayList<>();
        List<Integer> p1 = new ArrayList<>();
        List<Integer> p2 = new ArrayList<>();
        List<Integer> p3 = new ArrayList<>();
        List<Integer> p4 = new ArrayList<>();
        List<Integer> p5 = new ArrayList<>();
        List<Integer> p6 = new ArrayList<>();
        //遍历
        allData.stream().forEach(a->{
            a.getData().stream().forEach(item->{
                t.add(Integer.parseInt(item.getT()));
                p1.add(Integer.parseInt(item.getP1()));
                p2.add(Integer.parseInt(item.getP2()));
                p3.add(Integer.parseInt(item.getP3()));
                p4.add(Integer.parseInt(item.getP4()));
                p5.add(Integer.parseInt(item.getP5()));
                p6.add(Integer.parseInt(item.getP6()));
            });
        });
        //存储所有的结果
        List<Integer> all = new ArrayList<>();
        all.addAll(t);
        all.addAll(p1);
        all.addAll(p2);
        all.addAll(p3);
        all.addAll(p4);
        all.addAll(p5);
        all.addAll(p6);

        //key = A K/V  不区分特码，小码，统计所有数字前10名
        getLimitCount(all,map,"A",limit);

        //key = T K/V  特码统计所有数字前10名
        getLimitCount(t,map,"T",limit);

        //key = P1 K/V  小码p1，统计所有数字前10名
        getLimitCount(p1,map,"P1",limit);

        //key = P2 K/V  小码p2，统计所有数字前10名
        getLimitCount(p2,map,"P2",limit);

        //key = P3K/V  小码p3，统计所有数字前10名
        getLimitCount(p3,map,"P3",limit);

        //key = P4 K/V  小码p4，统计所有数字前10名
        getLimitCount(p4,map,"P4",limit);

        //key = P5 K/V  小码p5，统计所有数字前10名
        getLimitCount(p5,map,"P5",limit);

        //key = P6 K/V  小码p6，统计所有数字前10名
        getLimitCount(p6,map,"P6",limit);

        return map;
   }

    /**
     * 获取以年为单位的出现次数数字的排名
     * return  key=TK/TV   value=数字集合/次数集合
     * @param year  年
     * @param limit  限制排名
     * @return
     */
    @Override
    public Map<String, List<Integer>> getYearAnalyCountData(int year, int limit) {

        LiuHeVo liuHeVo = liuHeDao.getDataByYear(year);
        List<LiuHeVo> list = new ArrayList<>();
        list.add(liuHeVo);

        return getCountData(list,limit);
    }

    /**
     * 统计方法
     * @param data  数据源
     * @param map   返回结果
     * @param limit 限制数
     * @param key map key值
     */
    private void getLimitCount(List<Integer> data, Map<String, List<Integer>> map, String key,int limit) {

        //分组统计次数
        Map<Integer, Long> collect = data.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //由于hashmap无序，所以在排序放入LinkedHashMap里(value升序)
        Map<String, Long> sortMap = new LinkedHashMap<>();
        collect.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                forEach(e -> sortMap.put(e.getKey()+"", e.getValue()));

        //获取排序后map的key集合
        List<Integer> keys = new LinkedList<>();
        sortMap.entrySet().stream().limit(limit).forEach(a->keys.add(Integer.parseInt(a.getKey())));


        //获取排序后map的value集合
        List<Integer> values = new LinkedList<>();
        sortMap.entrySet().stream().limit(limit).forEachOrdered(e -> values.add((e.getValue().intValue())));

        //把值放到map中
        map.put(key+"K",keys);
        map.put(key+"V",values);
    }
}
