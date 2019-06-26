package com.winston.aop.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Winston.Wang
 * @version 1.0
 * @Description: 需要添加注解的方法
 * @date 2019/6/19
 */
@Service
public class SecuredMethod {

    private Logger logger = LoggerFactory.getLogger(SecuredMethod.class);

    @Secured(isLocked = true)
    public void lockMethod(){

        logger.info("lockMethod");
    }

    /**
     * 默认就为false，不锁定
     */
    @Secured
    public void unLockMethod(){

        logger.info("unLockMethod");
    }
}
