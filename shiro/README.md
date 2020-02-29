#my shiro one
>大学时有接触过shiro也有写过一些demo。但是并没有真正的深入的了解过，实际应用。参加工作后觉得深入的学习一些技术还是比较有必要的。
#开启shiro之旅 概念模式

>言简意赅 shiro 是一个简单易上手轻量级的开源的java安全框架。可用非web项目和web项目，统称起来就是通用。目前shiro的版本已经到了1.5.1版本了

##shiro 所有组件图
<center>  <!--开始居中对齐-->

![官网](https://shiro.apache.org/assets/images/ShiroFeatures.png "官网")
</center> 

>**Authentication：** 身份认证/登录，验证用户是不是拥有相应的身份； Authorization：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用 户是否能做事情，常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用
 户对某个资源是否具有某个权限；
 
>**Session Manager：** 会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信
 息都在会话中；会话可以是普通 JavaSE 环境的，也可以是如 Web 环境的； Cryptography：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；

>**Web Support：** Web 支持，可以非常容易的集成到 Web 环境；
 >Caching：缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可以
 6 提高效率；

>**Concurrency：** shiro 支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能 把权限自动传播过去；

>**Testing：** 提供测试支持； Run As：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；

>**Remember Me：** 记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录
 了
 
#张开涛:记住一点，Shiro 不会去维护用户、维护权限；这些需要我们自己去设计/提供；然后通过相应的接口注入给 Shiro 即可

##shiro 执行流程图

<center>  <!--开始居中对齐-->

![官网](https://shiro.apache.org/assets/images/ShiroBasicArchitecture.png "官网")
</center> 

>**Subject：** 主体，代表了当前“用户”，这个用户不一定是一个具体的人，与当前应用交互
的任何东西都是 Subject，如网络爬虫，机器人等；即一个抽象概念；所有 Subject 都绑定 到 SecurityManager，与 Subject 的所有交互都会委托给 SecurityManager；
可以把 Subject 认为是一个门面；SecurityManager 才是实际的执行者；

>**SecurityManager：** 安全管理器；即所有与安全有关的操作都会与 SecurityManager 交互； 且它管理着所有 Subject；可以看出它是 Shiro 的核心，它负责与后边介绍的其他组件进行
交互，如果学习过 SpringMVC，你可以把它看成 DispatcherServlet 前端控制器； Realm：域，Shiro 从从 Realm 获取安全数据（如用户、角色、权限），就是说 SecurityManager
要验证用户身份，那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；

>**Realm：** 得到用户相应的角色/权限进行验证用户是否能进行操作；可以把 Realm 看 成 DataSource，即安全数据源。

##整体架构图
<center>

![官网](https://shiro.apache.org/assets/images/ShiroArchitecture.png "官网")

</center> 

>**Subject：** 主体，可以看到主体可以是任何可以与应用交互的“用户”。

>**SecurityManager ：** 相当于 SpringMVC 中 的 DispatcherServlet 或 者 Struts2 中 的 FilterDispatcher；是 Shiro 的心脏；所有具体的交互都通过 SecurityManager 进行控制；
它管 理着所有 Subject、且负责进行认证和授权、及会话、缓存的管理。

>**Authenticator：** 认证器，负责主体认证的，这是一个扩展点，如果用户觉得 Shiro 默认的
不好，可以自定义实现；其需要认证策略（Authentication Strategy），即什么情况下算用户
认证通过了。

>**Authrizer：** 授权器，或者访问控制器，用来决定主体是否有权限进行相应的操作；即控制
着用户能访问应用中的哪些功能； 

>**Realm：** 可以有 1 个或多个 Realm，可以认为是安全实体数据源，即用于获取安全实体的； 可以是 JDBC 实现，也可以是 LDAP 实现，或者内存实现等等；由用户提供；注意：Shiro
不知道你的用户/权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己
的 Realm；

>**SessionManager：** 如果写过 Servlet 就应该知道 Session 的概念，Session 呢需要有人去管理
它的生命周期，这个组件就是 SessionManager；而 Shiro 并不仅仅可以用在 Web 环境，也
可以用在如普通的 JavaSE 环境、EJB 等环境；所有呢，Shiro 就抽象了一个自己的 Session
来管理主体与应用之间交互的数据；这样的话，比如我们在 Web 环境用，刚开始是一台
Web 服务器；接着又上了台 EJB 服务器；这时想把两台服务器的会话数据放到一个地方，
这个时候就可以实现自己的分布式会话（如把数据放到 Memcached 服务器）。

>**SessionDAO：** DAO 大家都用过，数据访问对象，用于会话的 CRUD，比如我们想把 Session
保存到数据库，那么可以实现自己的 SessionDAO，通过如 JDBC 写到数据库；比如想把。
Session 放到 Memcached 中，可以实现自己的 Memcached SessionDAO；另外 SessionDAO
中可以使用 Cache 进行缓存，以提高性能。

>**CacheManager：** 缓存控制器，来管理如用户、角色、权限等的缓存的；因为这些数据基本
上很少去改变，放到缓存中后可以提高访问的性能 。

> **Cryptography：** 密码模块，Shiro 提高了一些常见的加密组件用于如密码加密/解密的

#简单的demo
>demo  干了啥？
>首先Realm是shiro的数据来源，第一个demo不搞那么复杂。初始shiro不定义自己的realm，直接使用shiro给我们定义好的realm，位于shiro-core jar下的realm.text. 
这个包下定义的realm都是从text(文件)加载数据源的其中还有 **IniRealm,TextConfigurationRealm,PropertiesRealm**
官网的示范是从使用iniRealm进行加载，在java中使用properties文件也是比较常见，所以我也会从  **PropertiesRealm**进行加载。大致做一下几件事
1. 引入依赖，使用版本1.5.1
2. 使用Factory初始化不同类型文件realm加载方式。
3. 建立一个测试类，初始化shiro
4. 测试方法测试登录成功流程
5. 测试方法测试登录失败对应的异常返回
> pom.xml 引入依赖
```text
 <dependencies>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-core</artifactId>
            <version>1.5.1</version>
        </dependency>
         <dependency>
             <groupId>junit</groupId>
             <artifactId>junit</artifactId>
             <version>4.12</version>
             <scope>test</scope>
          </dependency>
    </dependencies>
```

1. resources 资源目录下新建shiro-users.properties文件,shiro默认加载的路径是：**classpath:shiro-users.properties**

2. shiro-users.properties写入以下内容
```text
# =============================================================================
# properties 格式配置
# =============================================================================

# -----------------------------------------------------------------------------
# 用户配置格式  user.登录名=认证密码:角色
# user.username=password
# -----------------------------------------------------------------------------
user.admin = 123456,admin
# -----------------------------------------------------------------------------
# 角色和权限配置方式
# role.roleName = perm1, perm2, ..., permN
# -----------------------------------------------------------------------------
role.admin = *
```
3.共有测试代码：初始化shiro
```java

```

> 新建shiro.ini 写入如下内容
```text
# =============================================================================
# INI 格式配置
# =============================================================================

# -----------------------------------------------------------------------------
# 用户配置格式  登录名=认证密码,角色名
# username = password, role1, role2, ..., roleN
# -----------------------------------------------------------------------------
[users]
admin = 123456, admin

# -----------------------------------------------------------------------------
# 角色和权限配置方式    角色名=权限
# roleName = perm1, perm2, ..., permN
# -----------------------------------------------------------------------------
[roles]
admin = *
```
>MyRealmFactory 代码
```java
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
```
> 注意：
+ 引入的SecurityManager 要用shiro 路径下的这个 org.apache.shiro.mgt.SecurityManager
+ IniSecurityManagerFactory已经过时不推荐使用可以采用和properties文件一样的加载方式使用InitRealm加载

>初始化测试代码
```java
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
     * 最后登出
     */
    @After
    public  void testLogout(){

        SecurityUtils.getSubject().logout();
    }
```

>认证成功测试代码
```java
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
```

>认证异常测试代码
```java
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
```
>异常比较常用的分为两大类AccountException(账号异常),CredentialsException(凭证异常) 都是继承自AuthenticationException异常
+ AccountException(账号异常)
    1. DisabledAccountException/LockedAccountException  账号不可用/账号被锁定
    2. UnkonwnAccountException   账号不存在
    3. ConcurrentAccessException 并发访问异常（多个用户同时登录时抛出）
    4. ExcessiveAttemptsException 认证次数超过限制
+ CredentialsException(凭证异常)
    1. IncorrectCredentialsException 不正确的凭证    
    2. ExpiredCredentialsException 凭证过期 

 ---
 + 官方地址：https://shiro.apache.org
 + 参考：张开涛 跟我学 Shiro——http://jinnianshilongnian.iteye.com/
 + 项目地址：
