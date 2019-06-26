package com.winston.aop.logger;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 测试Aspect日志的类
 * @date 2019/6/15
 */
public class SimpleCalc {

    /**
     * 实现两个数相加
     * @author  Winston.Wang
     * @date 2019/6/15
     * @param
     * @param
     * @return
     **/

    public  int add(int a,int b){

        int c = a+b;
        return  c;
    }

    /**
     * 相除
     * @param a
     * @param b
     * @return
     */
    public  int div(int a,int b){

        return  a/b;
    }
}
