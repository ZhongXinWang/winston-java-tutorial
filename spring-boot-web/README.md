# 修改Spring-boot的banner，快速启动web工程
>开始学习spring-boot，见识spring-boot的风采。见识一下“约定优于配置”的快速启动spring开发框架，摆脱spring+spring MVC的一系列的xml配置

#>使用maven构建spring boot
```
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.3.RELEASE</version>
    </parent>
```
>在pom.xml文件中继承spring boot的父pom，指定一下版本2.1.3.RELEASE，父pom里面会将编译版本，spring的版本指定好。
 在2.1.3.RELEASE版本的spring-boot里面依赖的spring版本是5.1.5

#修改spring-boot启动的banner
>spring boot在启动的时候会打印出banner。这个是可以自定义的。自己实现MyBanner类实现spring boot 的Banner接口重写printBanner方法，代码如下：



```
package com.winston.spring.banner;
/**
* x修改spring boot Banner
* @author Winston.Wang
* @date 2019/7/22
* @version 1.0
**/
public class MyBanner implements Banner {

    private static final String[] BANNER = { "",
            "  .   ____          _            __ _ _",
            " /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\",
            "( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\",
            " \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )",
            "  '  |____| .__|_| |_|_| |_\\__, | / / / /",
            " =========|_|==============|___/=/_/_/_/" };

    private static final String[] MY_BANNER = {"",
  "          /// ___|   _ __    _ __  (_)  _ __     __ _    | __ )    ___     ___   | |_    /// ___|   /// _ \\    | ____|   __ _   ___   _   _ ",
 "\\___ \\  | '_ \\  | '__| | | | '_ \\   // _` |   |  _ \\   // _ \\   // _ \\  | __|   \\___ \\  | | | |   |  _|    / _` | / __| | | | |",
  "  ___) | | |_) | | |    | | | | | | | (_| |   | |_) | | (_) | | (_) | | |_     ___) | | |_| |   | |___  | (_| | \\__ \\ | |_| |",
  "          |____//  | .__//  |_|    |_| |_| |_|  \\__, |   |____//   \\___//   \\___//   \\__|   |____//   \\___//    |_____|  \\__,_| |___/  \\__, |",
  "          |_|                         |___/                                                                             |___/"
};

    private static final String SPRING_BOOT = " :: Spring Boot SO Easy :: ";

    private static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass,
                            PrintStream printStream) {
        for (String line : MY_BANNER) {
            printStream.println(line);
        }
        String version = SpringBootVersion.getVersion();
        version = (version != null) ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder();
        while (padding.length() < STRAP_LINE_SIZE
                - (version.length() + SPRING_BOOT.length())) {
            padding.append(" ");
        }

        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT,
                AnsiColor.DEFAULT, padding.toString(), AnsiStyle.FAINT, version));
        printStream.println();
    }

}
```
#启动一个spring boot
>新建一个普通的Application.java类，这个类是一个入口类，一定要带main方法。启动这个类就能像以前一样启动一个一堆xml配置的spring工程了
```
import com.winston.spring.banner.MyBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
* spring boot启动类
* @author Winston.Wang
* @date 2019/7/21
* @version 1.0
**/
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.winston")
public class Application {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBanner(new MyBanner());
        springApplication.run(args);
    }
}
```
---
>使用spring的时候，要启动一个web工程的话，要在web.xml中做如下配置：
```
  <!-- 引入主配置文件 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-config.xml</param-value>
    </context-param>
     <!--Spring上下文监听器-->
        <listener>
            <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
     </listener>
      <!-- Spring字符编码过滤器 - 解决中文问题 -->
      <filter>
             <filter-name>encodingFilter</filter-name>
             <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
             <init-param>
                 <param-name>encoding</param-name>
                 <param-value>UTF-8</param-value>
             </init-param>
             <init-param>
                 <param-name>forceEncoding</param-name>
                 <param-value>true</param-value>
             </init-param>
      </filter>
      <filter-mapping>
             <filter-name>encodingFilter</filter-name>
             <url-pattern>/*</url-pattern>
       </filter-mapping>
        <!-- SpringMVC 前端控制器 DispatcherServlet 配置 -->
      <servlet>
               <servlet-name>springmvc</servlet-name>
               <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
               <init-param>
                   <param-name>contextConfigLocation</param-name>
                   <param-value>classpath:spring-mvc.xml</param-value>
               </init-param>
               <load-on-startup>1</load-on-startup>
               <async-supported>true</async-supported>
       </servlet>
       <servlet-mapping>
               <servlet-name>springmvc</servlet-name>
               <url-pattern>/</url-pattern>
       </servlet-mapping>

```
>web.xml中还配置了spring和spring mvc对应的需要扫描加载的xml文件。这样一来启动一个spring web项目就会引入很多xml文件，这样会导致了项目很难去维护。
使用spring boot的"约定优于配置"，这些重复的配置spring boot已经帮我们做了，如果你是一个没有特殊配置的spring 项目，那么使用spring boot可以大大节省
搭建工程的时间，spring boot已经将常规的配置都给你配置好了，你只需要简单的引入依赖即可。

 #快速启动一个spring web项目，只需要引入下面的依赖
```
     <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
     </dependencies>
```
> 引入spring-boot-starter-web（快速启动web）依赖之后，这个依赖里面会依赖对应的spring web，spring mvc，tomcat一整套启动一个web项目所需要的依赖。

 #编写一个简单的HelloController类
 ```
/**
* HelloWorld控制层
* @author Winston.Wang
* @date 2019/7/21
* @version 1.0
**/
@RestController
public class HelloController {

    @RequestMapping("/helloWorld")
    public  String  home(){

        return "Hello World";
    }

}
 ```
 >使用我们Application.java启动类启动，可以看到控制台中tomcat监听的端口是8080，访问 http://localhost:8080/helloWorld 就能看到“Hello World”
在spring boot中，引入spring-boot-starter-web，spring boot会帮我们new  Tomcat()对象，其中Tomcat默认监听的端口是8080。其中的Tomcat是使用New出来的

---
>从上面我们看到了Tomcat是New出来的，监听的端口是8080，这就是spring boot和我们约定的配置，port=8080，如果有需要我们可以修改。往下看

#如何寻找spring-Boot对应启动模块的配置参数
>修改默认配置参数，spring-boot扫描配置文件是通过spring-boot包下的spring.factories定义的ConfigFileApplicationListener类来加载配置文件。默认会扫描classpath:/,classpath:/config/,file:./,file:./config/   
下面的application*.properties，application*.yml后缀的文件作为配置文件所以我们只需要在项目的resource目录下，新建一个application.properties文件来作为修改约定的配置文件。内容如下
```
###tomcat配置
#端口
server.port=8081
#绑定IP
server.address=localhost
#超时时间
connectionTimeout=5000
```
>重新启动工程就能看到端口修改成我们所需要的8081端口。
项目代码已经同步到github，地址：https://github.com/ZhongXinWang/winston-java-tutorial

# spring-boot约定大于配置，偏要修改配置
>spring-boot约定大于配置,大部分情况下约定的配置足以满足你的需要，但是我们必须学会如何在约定的配置无法满足我们的时候，我们能修改约定的配置。
spring-boot扫描配置文件是通过spring-boot包下的spring.factories定义的ConfigFileApplicationListener类来加载
配置文件。默认会扫描classpath:/,classpath:/config/,file:./,file:./config/   下面的application*.properties，application*.yml后缀的文件作为配置文件
 所以我们只需要在项目的resource目录下，新建一个application.properties文件来作为修改约定的配置文件。
 
## application.properties配置文件有了，如何修改

>spring-boot的默认配置都在spring-boot-autoconfigure jar包下。这个包下的每个子包的配置都有一个*Properties类，下面以寻找tomcat配置为例

##如何寻找tomcat配置的字段修改配置
>org.springframework.boot.autoconfigure.web.ServerProperties类，是Tomcat配置类上面有个注解@ConfigurationProperties(prefix = "server", ignoreUnknownFields = true)定义了配置的前缀
在application.properties文件,输入：server.port=8081就能修改tomcat启动的默认8080端口为8081。就这样轻松的修改默认的配置。其他快速启动模块的配置都类似。寻找*Properties类
##Tomcat的一些配置项
```
# EMBEDDED SERVER CONFIGURATION (ServerProperties)
server.address= localhost  # 服务绑定的IP.
server.compression.enabled=false # If response compression is enabled.
server.compression.excluded-user-agents= # List of user-agents to exclude from compression.
server.compression.mime-types= # Comma-separated list of MIME types that should be compressed. For instance `text/html,text/css,application/json`
server.compression.min-response-size= # Minimum response size that is required for compression to be performed. For instance 2048
server.connection-timeout= # 连接超时配置.
server.display-name=application # 应用名
server.max-http-header-size=0 # Maximum size in bytes of the HTTP message header.
server.error.include-exception=false # Include the "exception" attribute.
server.error.include-stacktrace=never # When to include a "stacktrace" attribute.
server.error.path=/error # Path of the error controller.
server.error.whitelabel.enabled=true # Enable the default error page displayed in browsers in case of a server error.
server.jetty.acceptors= # Number of acceptor threads to use.
server.jetty.accesslog.append=false # Append to log.
server.jetty.accesslog.date-format=dd/MMM/yyyy:HH:mm:ss Z # Timestamp format of the request log.
server.jetty.accesslog.enabled=false # Enable access log.
server.jetty.accesslog.extended-format=false # Enable extended NCSA format.
server.jetty.accesslog.file-date-format= # Date format to place in log file name.
server.jetty.accesslog.filename= # Log filename. If not specified, logs will be redirected to "System.err".
server.jetty.accesslog.locale= # Locale of the request log.
server.jetty.accesslog.log-cookies=false # Enable logging of the request cookies.
server.jetty.accesslog.log-latency=false # Enable logging of request processing time.
server.jetty.accesslog.log-server=false # Enable logging of the request hostname.
server.jetty.accesslog.retention-period=31 # Number of days before rotated log files are deleted.
server.jetty.accesslog.time-zone=GMT # Timezone of the request log.
server.jetty.max-http-post-size=0 # Maximum size in bytes of the HTTP post or put content.
server.jetty.selectors= # Number of selector threads to use.
server.port=8080 # 监听端口，默认8080.
server.server-header= # Value to use for the Server response header (no header is sent if empty)
server.use-forward-headers= # If X-Forwarded-* headers should be applied to the HttpRequest.
server.servlet.context-parameters.*= # Servlet context init parameters
server.servlet.context-path= # Context path of the application.
server.servlet.jsp.class-name=org.apache.jasper.servlet.JspServlet # The class name of the JSP servlet.
server.servlet.jsp.init-parameters.*= # Init parameters used to configure the JSP servlet
server.servlet.jsp.registered=true # Whether or not the JSP servlet is registered
server.servlet.path=/ # Path of the main dispatcher servlet.
server.session.cookie.comment= # Comment for the session cookie.
server.session.cookie.domain= # Domain for the session cookie.
server.session.cookie.http-only= # "HttpOnly" flag for the session cookie.
server.session.cookie.max-age= # Maximum age of the session cookie in seconds.
server.session.cookie.name= # Session cookie name.
server.session.cookie.path= # Path of the session cookie.
server.session.cookie.secure= # "Secure" flag for the session cookie.
server.session.persistent=false # Persist session data between restarts.
server.session.servlet.filter-order=-2147483598 # Session repository filter order.
server.session.servlet.filter-dispatcher-types=ASYNC, ERROR, REQUEST # Session repository filter dispatcher types.
server.session.store-dir= # Directory used to store session data.
server.session.timeout= # Session timeout in seconds.
server.session.tracking-modes= # Session tracking modes (one or more of the following: "cookie", "url", "ssl").
server.ssl.ciphers= # Supported SSL ciphers.
server.ssl.client-auth= # Whether client authentication is wanted ("want") or needed ("need"). Requires a trust store.
server.ssl.enabled= # Enable SSL support.
server.ssl.enabled-protocols= # Enabled SSL protocols.
server.ssl.key-alias= # Alias that identifies the key in the key store.
server.ssl.key-password= # Password used to access the key in the key store.
server.ssl.key-store= # Path to the key store that holds the SSL certificate (typically a jks file).
server.ssl.key-store-password= # Password used to access the key store.
server.ssl.key-store-provider= # Provider for the key store.
server.ssl.key-store-type= # Type of the key store.
server.ssl.protocol=TLS # SSL protocol to use.
server.ssl.trust-store= # Trust store that holds SSL certificates.
server.ssl.trust-store-password= # Password used to access the trust store.
server.ssl.trust-store-provider= # Provider for the trust store.
server.ssl.trust-store-type= # Type of the trust store.
server.tomcat.accept-count= # Maximum queue length for incoming connection requests when all possible request processing threads are in use.
server.tomcat.accesslog.buffered=true # Buffer output such that it is only flushed periodically.
server.tomcat.accesslog.directory=logs # Directory in which log files are created. Can be relative to the tomcat base dir or absolute.
server.tomcat.accesslog.enabled=false # Enable access log.
server.tomcat.accesslog.file-date-format=.yyyy-MM-dd # Date format to place in log file name.
server.tomcat.accesslog.pattern=common # Format pattern for access logs.
server.tomcat.accesslog.prefix=access_log # Log file name prefix.
server.tomcat.accesslog.rename-on-rotate=false # Defer inclusion of the date stamp in the file name until rotate time.
server.tomcat.accesslog.request-attributes-enabled=false # Set request attributes for IP address, Hostname, protocol and port used for the request.
server.tomcat.accesslog.rotate=true # Enable access log rotation.
server.tomcat.accesslog.suffix=.log # Log file name suffix.
server.tomcat.additional-tld-skip-patterns= # Comma-separated list of additional patterns that match jars to ignore for TLD scanning.
server.tomcat.background-processor-delay=30 # Delay in seconds between the invocation of backgroundProcess methods.
server.tomcat.basedir= # Tomcat base directory. If not specified a temporary directory will be used.
server.tomcat.internal-proxies=10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|\\
        192\\.168\\.\\d{1,3}\\.\\d{1,3}|\\
        169\\.254\\.\\d{1,3}\\.\\d{1,3}|\\
        127\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|\\
        172\\.1[6-9]{1}\\.\\d{1,3}\\.\\d{1,3}|\\
        172\\.2[0-9]{1}\\.\\d{1,3}\\.\\d{1,3}|\\
        172\\.3[0-1]{1}\\.\\d{1,3}\\.\\d{1,3} # regular expression matching trusted IP addresses.
server.tomcat.max-connections= # Maximum number of connections that the server will accept and process at any given time.
server.tomcat.max-http-header-size=0 # Maximum size in bytes of the HTTP message header.
server.tomcat.max-http-post-size=0 # Maximum size in bytes of the HTTP post content.
server.tomcat.max-threads=0 # Maximum amount of worker threads.
server.tomcat.min-spare-threads=0 # Minimum amount of worker threads.
server.tomcat.port-header=X-Forwarded-Port # Name of the HTTP header used to override the original port value.
server.tomcat.protocol-header= # Header that holds the incoming protocol, usually named "X-Forwarded-Proto".
server.tomcat.protocol-header-https-value=https # Value of the protocol header that indicates that the incoming request uses SSL.
server.tomcat.redirect-context-root= # Whether requests to the context root should be redirected by appending a / to the path.
server.tomcat.remote-ip-header= # Name of the http header from which the remote ip is extracted. For instance `X-FORWARDED-FOR`
server.tomcat.uri-encoding=UTF-8 # Character encoding to use to decode the URI.
server.undertow.accesslog.dir= # Undertow access log directory.
server.undertow.accesslog.enabled=false # Enable access log.
server.undertow.accesslog.pattern=common # Format pattern for access logs.
server.undertow.accesslog.prefix=access_log. # Log file name prefix.
server.undertow.accesslog.rotate=true # Enable access log rotation.
server.undertow.accesslog.suffix=log # Log file name suffix.
server.undertow.buffer-size= # Size of each buffer in bytes.
server.undertow.direct-buffers= # Allocate buffers outside the Java heap.
server.undertow.io-threads= # Number of I/O threads to create for the worker.
server.undertow.eager-filter-init=true # Whether servlet filters should be initialized on startup.
server.undertow.max-http-post-size=0 # Maximum size in bytes of the HTTP post content.
server.undertow.worker-threads= # Number of worker threads.
```
# spring-boot-starter-web（快速启动web）默认使用tomcat，修改为jetty
##切换为使用jetty，修改pom依赖，要排除tomcat的依赖
```
<dependencies>
    <!-- spring-boot使用jetty容器配置begin -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <!-- 排除默认的tomcat,引入jetty容器. -->
        <exclusions>
           <exclusion>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-tomcat</artifactId>
           </exclusion>
        </exclusions>
    </dependency>
    <!-- jetty 容器. -->
    <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-jetty</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <!-- spring-boot使用jetty容器配置end -->
</dependencies>
```
>经过以上的操作就能将web容器修改为jetty了