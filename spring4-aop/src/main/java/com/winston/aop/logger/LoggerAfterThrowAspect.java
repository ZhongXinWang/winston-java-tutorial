package com.winston.aop.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 方法执行出现异常，把异常输出
 * @date 2019/6/15
 */
public class LoggerAfterThrowAspect {

    private Logger logger = LoggerFactory.getLogger(LoggerBeforeAspect.class);

    /**
     *  方法执行出现异常，把异常输出,不能喝around一起执行，应为around已经将异常抛出
     * @author  Winston.Wang
     * @date 2019/6/15
     * @param
     * @param
     * @return
     **/
    public void afterThrow(Exception exception){

        logger.info("---------------------------执行方法抛出异常："+exception.getMessage()+"-------------------------------");
    }
}
