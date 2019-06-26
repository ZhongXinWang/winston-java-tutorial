package com.winston.aop.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 用一句话描述我是干啥的
 * @date 2019/6/15
 */
public class LoggerAfterAspect {

    private Logger logger = LoggerFactory.getLogger(LoggerBeforeAspect.class);

    /**
     * 用一句话描述我是干啥的
     * @author  Winston.Wang
     * @date 2019/6/15
     * @param
     * @param
     * @return
     **/
    public void afterAdvice(Object args1,Object args2){

        logger.info("---------------------------方法调用结束输出最后日志-------------------------------");
    }
}
