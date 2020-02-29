package com.winston.vo;

import lombok.Data;

import java.io.Serializable;
/**
* 具体数据
* @author Winston.Wang
* @date 2019/8/23
* @version 1.0
**/
@Data
public class LiuHeData  implements Serializable {

    /**
     * 日期
     */
    private String day;
    /**
     * 期数
     */
    private String no;
    /**
     * 小马 1
     */
    private String p1;
    /**
     * 小马 2
     */
    private String p2;
    /**
     * 小马 3
     */
    private String p3;
    /**
     * 小马 4
     */
    private String p4;
    /**
     * 小马 5
     */
    private String p5;
    /**
     * 小马 6
     */
    private String p6;
    /**
     * 特码
     */
    private String t;

    /**
     * 本年生肖
     */
    private String xn;

    /**
     * 小马生肖 1
     */
    private String p1xn;
    /**
     * 小马生肖 2
     */
    private String p2xn;
    /**
     * 小马生肖 3
     */
    private String p3xn;
    /**
     * 小马生肖 4
     */
    private String p4xn;
    /**
     * 小马生肖 5
     */
    private String p5xn;
    /**
     * 小马生肖 6
     */
    private String p6xn;
    /**
     * 特码生肖
     */
    private String txn;
}
