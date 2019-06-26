package com.winston.callable;

import java.util.concurrent.Callable;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 实现Callable执行完线程之后可以返回结果
 * @date 2019/6/26
 */
public class Task implements Callable<Integer> {
    @Override
    public   Integer call() throws Exception {

        System.out.println("子线程在进行计算:"+Thread.currentThread());
        Thread.sleep(3000);
            int sum = 0;
            for(int i=0;i<=100;i++){
                sum += i;
            }
            return sum;

    }
}
