package com.winston.factory;

import org.apache.shiro.config.IniFactorySupport;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.PropertiesRealm;

/**
* Realm 工厂类
* @author Winston.Wang
* @date 2020/2/29
* @version 1.0
**/
public class MyRealmFactory {

    /**
    * 初始化不同realm  SecurityManager
    * @author Winston.Wang
    * @date 2020/2/29
    **/
    public static  org.apache.shiro.mgt.SecurityManager createInstance(String realmResource) {

        /*
        加载ini文件方式
        此处的IniSecurityManagerFactory已经过时不推荐使用可以采用和properties文件一样的加载方式使用InitRealm加载
        */
        if(realmResource.endsWith("ini")){
            IniFactorySupport<org.apache.shiro.mgt.SecurityManager> factory= new IniSecurityManagerFactory(realmResource);
            return factory.getInstance();
        }
        /**
         *   加载 Properties 文件，采用PropertiesRealm加载方式
         */
        //创建PropertiesRealm
        PropertiesRealm propertiesRealm = new PropertiesRealm();
        //不设置 默认加载classpath:shiro-users.properties路径
        propertiesRealm.setResourcePath(realmResource);
        propertiesRealm.onInit();

        //使用默认的安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(propertiesRealm);

        return defaultSecurityManager;
    }

}
