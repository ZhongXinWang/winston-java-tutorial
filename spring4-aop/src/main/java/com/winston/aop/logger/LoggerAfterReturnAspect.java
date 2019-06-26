package com.winston.aop.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 方法执行结束把返回参数输出
 * @date 2019/6/15
 */
public class LoggerAfterReturnAspect {

    private Logger logger = LoggerFactory.getLogger(LoggerBeforeAspect.class);

    /**
     *  方法执行结束把返回参数输出
     * @author  Winston.Wang
     * @date 2019/6/15
     * @param
     * @param
     * @return
     **/
    public void afterReturn(Object returnValue){

        logger.info("---------------------------方法调用结束返回值是："+returnValue+"-------------------------------");
    }
}
