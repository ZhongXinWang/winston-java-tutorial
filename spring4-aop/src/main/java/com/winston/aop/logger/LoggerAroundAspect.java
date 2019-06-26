package com.winston.aop.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 方法执行前到执行结束的环绕通知
 * @date 2019/6/15
 */
public class LoggerAroundAspect {

    private Logger  logger = LoggerFactory.getLogger(LoggerAroundAspect.class);

    /**
     * 用一句话描述我是干啥的
     * ProceedingJoinPointo切入点
     * @author  Winston.Wang
     * @date 2019/6/15
     * @param
     * @param
     * @return
     **/

    public Object aroundAdvice(ProceedingJoinPoint point)  throws  Throwable{

        logger.info("通过方法的参数："+point.getArgs());
        //获取执行后的结果
        Object result = point.proceed();
        logger.info("方法执行后的结果："+result);
        return result;

    }

}
