package com.winston.entity.test;

import com.winston.entity.CommonUser;
import com.winston.entity.LombokUser;
import org.junit.Test;

public class TestLombok {

    /**
    * 测试普通有Set/Get的方法
    * @author Winston.Wang
    * @date 2019/7/20
    **/
    @Test
    public  void testCommonUser(){

        CommonUser user = new CommonUser();
        user.setAge(1);
        user.setName("测试");
        System.out.println(user);
    }

    /**
    * 测试使用Data
    * @author Winston.Wang
    * @date 2019/7/20
    * @param
    * @param
    * @return
    **/
    @Test
    public  void testLombokUser(){

        LombokUser user = new LombokUser();
        user.setAge(1);
        user.setName("hello");
        System.out.println(user);
    }
}
