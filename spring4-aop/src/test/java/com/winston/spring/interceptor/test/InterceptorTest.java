package com.winston.spring.interceptor.test;

import com.winston.spring.interceptor.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 用一句话描述我是干啥的
 * @date 2019/6/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-interceptor-example.xml"})
public class InterceptorTest {

    @Autowired
    private LoginService loginService;

    /**
     * 测试方法拦截器
     * @author  Winston.Wang
     * @date 2019/6/16
     **/
    @Test
    public  void testMyMethodIntrceptor(){

        loginService.getUser();
    }

    /**
     * 测试啥呢
     * @author  Winston.Wang
     * @date 2019/6/20
     **/
    @Test
    public  void test(){

        System.out.println(getNextDate(false));
    }

    /**
     * 获取下一天
     * @return
     */
    private Date getNextDate(boolean flag) {

        Date date = new Date();
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, +1);
            if (flag) {
                // 指定日期往前推算,则设置日期的时间最小值
                cal.set(Calendar.HOUR_OF_DAY, 0);// 小时
                cal.set(Calendar.MINUTE, 0);// 分钟
                cal.set(Calendar.SECOND, 0);// 秒
                cal.set(Calendar.MILLISECOND, 0);// 毫秒
            }else{
                // 指定日期往后推算,则设置日期的时间最大值
                cal.set(Calendar.HOUR_OF_DAY, 23);// 小时
                cal.set(Calendar.MINUTE, 59);// 分钟
                cal.set(Calendar.SECOND, 59);// 秒
                cal.set(Calendar.MILLISECOND, 999);// 毫秒
            }
            date = cal.getTime();
        }
        return date;
    }
}
