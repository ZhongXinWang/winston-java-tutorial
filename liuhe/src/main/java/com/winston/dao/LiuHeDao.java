package com.winston.dao;

import com.winston.vo.LiuHeVo;

import java.util.List;

/**
* dao服务
* @author Winston.Wang
* @date 2019/8/23
* @version 1.0
**/
public interface LiuHeDao {

    /**
     * 通过年获取数据
     * @param year
     * @return
     */
    LiuHeVo getDataByYear(int year);

    /**
     * 获取所有记录
     * @return
     */
    List<LiuHeVo> getAllData();

}
