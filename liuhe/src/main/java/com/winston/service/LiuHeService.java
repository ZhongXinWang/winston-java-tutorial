package com.winston.service;

import com.winston.vo.LiuHeVo;

import java.util.List;
import java.util.Map;

/**
* 查询服务
* @author Winston.Wang
* @date 2019/8/23
* @version 1.0
**/
public interface LiuHeService {


    /**
     * 通过年获取数据
     * @param year
     * @return
     */
    LiuHeVo getDataByYear(int year);

    /**
     * 获取所有记录，以年作为key
     * @return
     */
    List<Map<Integer,List<Integer>>> getAllData();

    /**
     *  获取所有记录
     * @return
     */
    List<LiuHeVo> getData();

    /**
     * 获取出现次数最多数字的排名
     * key=All/T/P1/P2
     */
    Map<String,List<Integer>>  getAnalyCountData(int limit);

    /**
     * 获取以年为单位的出现次数最多数字的排名
     * key=All/T/P1/P2
     */
    Map<String,List<Integer>>  getYearAnalyCountData(int year,int limit);
}
