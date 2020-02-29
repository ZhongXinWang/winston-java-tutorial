# 初始Spring-boot
>学习怎么使用spring-boot快速启动spring的jdbc项目。使用spring自带的jdbc操作数据库

#版本依赖
```
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.3.RELEASE</version>
    </parent>
    2.1.3.RELEASE 版本的spring-boot里面依赖的spring版本是5.1.5,并没有将定义的5.1.8依赖进来
    引入快速启动web依赖：
     <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
     </dependency>
     <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-jdbc</artifactId>
     </dependency>
```
>使用spring-boot-start-test做Service单元测试
>使用注解：
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
Application是启动的主类
```
package com.winston.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.winston"})
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);
    }
}

```
```
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class CommonDaoTest {

    @Resource
    private CommonDao commonDao;

    /**
    * 测试插入
    * @author Winston.Wang
    * @date 2019/7/24
    * @version 1.0
    **/
    @Test
    public  void testInsert(){

        String sql = "insert into test(name,age) values(?,?);";
        int count = commonDao.insert(sql,"lisi",20);
        Assert.assertEquals(count,1);
    }

    /**
    * 测试更新
    * @author Winston.Wang
    * @date 2019/7/24
    * @version 1.0
    **/
    @Test
    public  void testUpdate(){


    }

}
```
>jdbc配置数据源,对应的配置在DataSourceProperties