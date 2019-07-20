package com.winston.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Lombok添加注解的类
* @author Winston.Wang
* @date 2019/7/20
* @version 1.0
**/
@Data
@NoArgsConstructor
public class LombokUser {
    /**
     * 年龄
     */
    private int age;
    /**
     * 姓名
     */
    private String name;

}
