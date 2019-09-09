package com.winston.service;

import com.winston.vo.LiuHeVo;

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


}
