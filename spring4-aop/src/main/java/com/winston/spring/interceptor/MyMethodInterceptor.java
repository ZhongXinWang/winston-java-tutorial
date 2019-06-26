package com.winston.spring.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 使用spring方法拦截器
 * @date 2019/6/16
 */
public class MyMethodInterceptor  implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(MyMethodInterceptor.class);
    /**
     * 用一句话描述我是干啥的
     * @author  Winston.Wang
     * @date 2019/6/16
     * @param
     * @param
     * @return
     **/
    
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if("getUser".equals(methodInvocation.getMethod().getName())){

            logger.error("请先登录再获取信息");
            return null;
        }
        //往下执行
        return methodInvocation.proceed();
    }
}
