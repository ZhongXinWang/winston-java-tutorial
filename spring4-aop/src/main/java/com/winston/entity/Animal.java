package com.winston.entity;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 用一句话描述我是干啥的
 * @date 2019/6/12
 */
public class Animal {
    /**
     * 动物名称
     */
    private String name;
    /**
     * 动物类别
     */
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String who(){

        return name+"---"+category;
    }

}
