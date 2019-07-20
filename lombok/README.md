# Lombok
>Lombok是一个Java库，能自动插入编辑器并构建工具，简化Java开发。通过添加注解的方式，
getter，setter或eques方法，同时可以自动化日志变量。主要使用Lombok工具简化实体类，
少写一些，getter和setter的方法。

#IntelliJ安装Lombok插件在使用get/set的方法是不会报错

#原理
>Lombok本质上就是一个实现了“JSR 269 API”的程序。在使用javac的过程中，它产生作用的具体流程如下：

>1.javac对源代码进行分析，生成了一棵抽象语法树（AST）
2.运行过程中调用实现了“JSR 269 API”的Lombok程序
此时Lombok就对第一步骤得到的AST进行处理，找到@Data注解所在类对应的语法树（AST），然后修改该语法树（AST），增加getter和setter方法定义的相应树节点
javac使用修改后的抽象语法树（AST）生成字节码文件，即给class增加新的节点（代码块）
通过读Lombok源码，发现对应注解的实现都在HandleXXX中，比如@Getter注解的实现在HandleGetter.handle()。还有一些其它类库使用这种方式实现，比如Google Auto、Dagger等等。

#基本使用
>给类添加上@Data注解
```
 @Data
 public class LombokUser {
     /**
      * 年龄
      */
     private int age;
     /**
      * 姓名
      */
     private String name;
 }
```
#Lombok注解说明

>val：用在局部变量前面，相当于将变量声明为final

>@NonNull：给方法参数增加这个注解会自动在方法内对该参数进行是否为空的校验，如果为空，则抛出NPE（NullPointerException）

>@Cleanup：自动管理资源，用在局部变量之前，在当前变量范围内即将执行完毕退出之前会自动清理资源，自动生成try-finally这样的代码来关闭流

>@Getter/@Setter：用在属性上，再也不用自己手写setter和getter方法了，还可以指定访问范围

>@ToString：用在类上，可以自动覆写toString方法，当然还可以加其他参数，例如@ToString(exclude=”id”)排除id属性，或者@ToString(callSuper=true, includeFieldNames=true)调用父类的toString方法，包含所有属性

>@EqualsAndHashCode：用在类上，自动生成equals方法和hashCode方法

>@NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor：用在类上，自动生成无参构造和使用所有参数的构造函数以及把所有@NonNull属性作为参数的构造函数，如果指定staticName = “of”参数，同时还会生成一个返回类对象的静态工厂方法，比使用构造函数方便很多

>@Data：注解在类上，相当于同时使用了@ToString、@EqualsAndHashCode、@Getter、@Setter和@RequiredArgsConstrutor这些注解，对于POJO类十分有用
```
 @Data
 public class LombokUser {
     /**
      * 年龄
      */
     private int age;
     /**
      * 姓名
      */
     private String name;
 }
```
>@Value：用在类上，是@Data的不可变形式，相当于为属性添加final声明，只提供getter方法，而不提供setter方法

>@Builder：用在类、构造器、方法上，为你提供复杂的builder APIs，让你可以像如下方式一样调用Person.builder().name("Adam Savage").city("San Francisco").job("Mythbusters").job("Unchained Reaction").build();更多说明参考Builder


>@SneakyThrows：自动抛受检异常，而无需显式在方法上使用throws语句

>@Synchronized：用在方法上，将方法声明为同步的，并自动加锁，而锁对象是一个私有的属性$lock或$LOCK，而java中的synchronized关键字锁对象是this，锁在this或者自己的类对象上存在副作用，就是你不能阻止非受控代码去锁this或者类对象，这可能会导致竞争条件或者其它线程错误

>@Getter(lazy=true)：可以替代经典的Double Check Lock样板代码

>@Log：根据不同的注解生成不同类型的log对象，但是实例名称都是log，有六种可选实现类


>@CommonsLog Creates log = org.apache.commons.logging.LogFactory.getLog(LogExample.class);

>@Log Creates log = java.util.logging.Logger.getLogger(LogExample.class.getName());

>@Log4j Creates log = org.apache.log4j.Logger.getLogger(LogExample.class);

>@Log4j2 Creates log = org.apache.logging.log4j.LogManager.getLogger(LogExample.class);

>@Slf4j Creates log = org.slf4j.LoggerFactory.getLogger(LogExample.class);

>@XSlf4j Creates log = org.slf4j.ext.XLoggerFactory.getXLogger(LogExample.class);


#优点：

>能通过注解的形式自动生成构造器、getter/setter、equals、hashcode、toString等方法，提高了一定的开发效率
让代码变得简洁，不用过多的去关注相应的方法
属性做修改时，也简化了维护为这些属性所生成的getter/setter方法等

#缺点：

>不支持多种参数构造器的重载
虽然省去了手动创建getter/setter方法的麻烦，但大大降低了源代码的可读性和完整性，降低了阅读源代码的舒适度
