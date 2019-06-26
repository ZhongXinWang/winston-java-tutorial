package com.winston.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: Aspect类用来织如我们的代码
 * @date 2019/6/19
 */
/**
 * 要想使用注解来实现Aspect代理类，
 * 把该类注册到AOP代理中，没有实现接口，这边使用的是cglib代理,  CglibAopProxy
 *  @EnableAspectJAutoProxy开启直接支持
 *  xml中，使用<aop:aspectj-autoproxy  proxy-target-class="true"/>   true：使用cglib代理，false使用jdk代理。不配
 *  根据是否实现接口匹配
 */
@Aspect
@EnableAspectJAutoProxy
public class SecuredAspect {

    private static Logger logger = LoggerFactory.getLogger(SecuredAspect.class);

    /**
     * 定义切点
     */
    @Pointcut(value = "@annotation(secured)")
    public void callAt(Secured secured){
    }
    /**
     * 定义通知类型
     * 通过ProceedingJoinPoint当前执行的方法校验通过可以往下执行
     * secured 注解参数
     */
    @Around("callAt(secured)")
    public Object around(ProceedingJoinPoint point, Secured secured) throws Throwable{

        if(secured.isLocked()){

            logger.info("aop拦截："+point.getSignature().toLongString()+"is Locked");
            return null;
        }
        logger.info("aop通过："+point.getSignature().toLongString()+"is UnLocked");
        //校验通过继续往下执行
        return point.proceed();
    }
}
