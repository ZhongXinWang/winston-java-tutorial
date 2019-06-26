package com.winston.executorservice.test;

import com.winston.callable.Task;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: java线程管理服务ExecutorService测试
 * @date 2019/6/26
 */
public class ExecutorServiceTest {

    private Logger logger = LoggerFactory.getLogger(ExecutorServiceTest.class);
    /**
     * a. 每次new Thread新建对象性能差。
     * b. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom。
     * c. 缺乏更多功能，如定时执行、定期执行、线程中断。
     * 相比new Thread，Java提供的四种线程池的好处在于：
     * a. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
     * b. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
     * c. 提供定时执行、定期执行、单线程、并发数控制等功能
     */
    /**
     * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
     * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     */

    /**
     * 测试啥呢
     * @author  Winston.Wang
     * @date 2019/6/26
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     **/
    @Test
    public  void testNewFixedThreadPool(){

        /**
         * isCancelled方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true。
         * isDone方法表示任务是否已经完成，若任务完成，则返回true；
         * get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；
         * get(long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null。
         */
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            Future<Integer> submit = executorService.submit(new Task());
            Future<Integer> submit1 = executorService.submit(new Task());
            Future<Integer> submit2 = executorService.submit(new Task());
            Future<Integer> submit3 = executorService.submit(new Task());
            Future<Integer> submit4 = executorService.submit(new Task());
            logger.info(submit.get()+"---"+submit1.get()+"---"+submit2.get()+"--"+submit3.get()+"---"+submit4.get());
            Assert.assertEquals(submit.get().intValue(), 5050);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
