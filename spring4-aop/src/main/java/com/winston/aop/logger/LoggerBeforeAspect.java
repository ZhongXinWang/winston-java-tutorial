package com.winston.aop.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 定义一个Aspect前置通知
 * @date 2019/6/15
 */
public class LoggerBeforeAspect {

    private Logger  logger = LoggerFactory.getLogger(LoggerBeforeAspect.class);

    public void beforeAdvice(Object args1,Object args2){

        logger.info("---------------------------日志输出前置通知,入参:a="+args1+"b="+args2);
    }

}
