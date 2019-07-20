package com.winston.entity;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 普通带有set/get的普通java类
 * @date 2019/6/21
 */
public class CommonUser {

    /**
     * 年龄
     */
    private int age;
    /**
     * 姓名
     */
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        return age+"-"+name;
    }
}
