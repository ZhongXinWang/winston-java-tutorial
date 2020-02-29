package com.winston.test;

import com.winston.factory.MyRealmFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
* shiro 第一个测试类
* @author Winston.Wang
* @date 2020/2/29
* @version 1.0
**/
public class Test20200229 {

    /**
     * 初始化,使用PropertiesRealm加载配置文件，初始化SecurityManager
     */
    @Before
    public  void testInit(){
        //String pathResource = "classpath:shiro-users.properties";
        String pathResource = "classpath:shiro.ini";
        //根据不同文件类型通过工厂类加载不同的Realm初始化SecurityManager
        SecurityManager instance = MyRealmFactory.createInstance(pathResource);
        //将管理器交由shiro管理
        SecurityUtils.setSecurityManager(instance);

    }

    /**
    *  测试认证成功流程
    * @author Winston.Wang
    * @date 2020/2/29
    **/
    @Test
    public  void testLoginSuccess(){
        // 1.创建简单的用户名和密码token
        UsernamePasswordToken token = new UsernamePasswordToken("admin","123456");
        // 2.login
        try{
            //认证
            SecurityUtils.getSubject().login(token);
            //认证成功,获取principal，判断是否是admin
            Object principal = SecurityUtils.getSubject().getPrincipal();
            //断言成功
            Assert.assertEquals("admin",principal.toString());

            //判断是否是admin角色， 结果成功
            Assert.assertTrue(SecurityUtils.getSubject().hasRole("admin"));
        }catch (AuthenticationException e){
            e.printStackTrace();
        }
    }

    /**
     *  测试登录不成功情况
     * @author Winston.Wang
     * @date 2020/2/29
     **/
    @Test
    public  void testLoginException(){
        //认证流程
        // 1.创建简单的用户名和密码token，输入错误的用户名
        UsernamePasswordToken token = new UsernamePasswordToken("admin","1234");
        // 2.login
        try{
            //认证
            SecurityUtils.getSubject().login(token);
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        }catch (UnknownAccountException e){
            System.out.println("未知账号");
        }catch (DisabledAccountException e){
            System.out.println("账号不可用");
        } catch (ExpiredCredentialsException e){
            System.out.println("凭证过期");
        }
    }

    /**
     * 最后登出
     */
    @After
    public  void testLogout(){

        SecurityUtils.getSubject().logout();
    }
}
