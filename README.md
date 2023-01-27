# SpringBoot2-atguigu

## 快速开始

### 1.引入依赖

```xml
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.4.RELEASE</version>
    </parent>


    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

    </dependencies>
```

### 2.创建SpringBoot启动器

```java
/**
 * @SpringBootApplication: 这是一个SpringBoot应用
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);

    }
}
```

### 3.创建一个控制器

```java
@ResponseBody    //在这里写表明该控制器内所有方法的返回值都作为响应内容直接响应到页面上
@Controller    //表明这是一个控制类Controller
public class HelloController {

    @ResponseBody    //注释写在这里表明方法返回值作为相应内容直接响应到页面上
    @RequestMapping("/hello")   //"/hello"请求会映射到这个方法上面
    //返回的数据类型是字符串，所以方法的返回值是String
    public String handle01(){
        return "Hello,world!";
    }

}
```

### 4.启动应用

在标注@SpringBootApplication的类上面点击运行即可，SpringBoot内置服务器Tomcat，所以我们不需要像写javaweb项目一样在在IDEA中配置Tomcat，

![](https://raw.githubusercontent.com/Lounwb/imgbed-picgo-repo/master/blogimg/202301182124556.png)

## SpringBoot的自动配置原理



## 注解

### 应用级注解

#### @SpringBootApplication

@SpringBootApplication表明这是一个SpringBoot应用，启动服务会加载此应用

卸载应用的同级目录下，如com.lounwb.crm

要想启动此项目还要在类里面加一个方法

```java
public static void main(String[] args) {
    //启动SpringBoot应用
        SpringApplication.run(MainApplication.class, args);

}
```

#### @Import

写在组件的上面，可以向容器中添加组件。例如

```java
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = false)
```

#### @ConditionalOnXXX

条件装配：Conditional代表在...条件下...

1. @ConditionalOnBean

   有xxx组件才会生效，可以标注在类上（组件），也可以标注在方法上

### 控制层注解

#### @RestController

```
@Controller
@ResponseBody
```

@RestController包含上面两个注解

#### @RequestMapping

此注解用于控制层中的方法上面，用于映射请求，服务器接收到请求会映射到方法里面执行方法。使用方法：@RequestMapping("/xxx.do")

#### @ResponseBody

如果写在控制器上使用此注解表明控制器内所有方法的返回值都作为响应内容直接响应到页面上

如果写在控制器的方法上面表明方法返回值作为相应内容直接响应到页面上

### JavaBean注解

#### @Bean

可以标注在方法上，表明这是一个JavaBean。向SpringBoot容器中添加组件，默认是**单实例**的

方法名作为bean的id，返回类型是组件类型class,返回值作为容器中的实例

如果不想让方法名作为bean的id，可以使用@Bean("xxx")

#### @Component

@Component注解表明一个类会作为组件类，并告知Spring要为这个类创建bean。

#### @ConfigurationProperties

读取application.properties核心配置文件中的内容，根据prifix来配置容器中的组件。前提必须为容器中的组件。

```java
@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {
    ...
}
```



### 配置类注解

#### @Configuration

​	告诉SpringBoot这是一个配置类，等同于一个xml配置文件，配置类本身也是SpringBoot的一个组件，由代理类生成。配置类里面的Bean默认是单实例的。如果你不想让SpringBoot去管理，让他不是单实例的，可以使用@Configuration(proxyBeanMethods = "false")   //代理bean方法默认为true。

​	原来proxyBeanMethods= "true"表明bean由springboot容器管理，拿的时候从容器中拿，容器中只有一个实例，所以是单实例模式。而如果设置proxyBeanMethods= “false”，每次调用方法都会new一个新对象，所以不是单实例。

1. 向容器中添加组件

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
       <bean id="user01" class="com.lounwb.boot.bean.User">
           <property name="name" value="zhangsan"></property>
           <property name="age" value="18"></property>
       </bean>
       <bean id="cat" class="com.lounwb.boot.bean.Pet">
           <property name="name" value="tomcat"></property>
       </bean>
   </beans>
   ```

   ```java
   @Configuration
   public class MyConfig {
       @Bean  //向容器中添加组件
       public User user01(){
           return new User("zhangsan", 18);
       }
       @Bean("tom")
       public Pet tomcat(){
           return new Pet("tomcat");
       }
   }
   ```

   ![](https://raw.githubusercontent.com/Lounwb/imgbed-picgo-repo/master/blogimg/202301191852671.png)

2. Full模式与Lite模式

   - 最佳实战
   - 配置 类组件之间无依赖关系用Lite模式加速容器启动过程，减少判断
   - 配置类组件之间有依赖关系，方法会被调用得到之前单实例组件，用Full模式

#### @ImportResources

导入资源文件（以前Sring的配置方法）

使用方法@ImportResources("路径...")

![](https://raw.githubusercontent.com/Lounwb/imgbed-picgo-repo/master/blogimg/202301201346263.png)

#### @EnableConfigurationProperties

使用效果同@Component+@ConfigurationProperties。不过这种方法适用于想把第三方库中的类注册到容器中，使用方法为：@EnableConfigurationProperties(xxx.class)。

## 配置文件

### YAML配置文件

### 静态资源配置

#### 静态资源目录

只要静态资源放在类路径下： called `/static` (or `/public` or `/resources` or `/META-INF/resources`

访问 ： 当前项目根路径/ + 静态资源名 



原理： 静态映射/**。

请求进来，先去找Controller看能不能处理。不能处理的所有请求又都交给静态资源处理器。静态资源也找不到则响应404页面

改变默认的静态资源路径： 以后请求需要加前缀才能访问到

```yaml
spring:
  mvc:
    static-path-pattern: /res/**
```

#### 静态资源存储策略

修改SpringBoot的静态资源存储位置

```yaml
spring:
  resources:
    static-locations: [classpath:/haha/]
```

现在必须静态资源扫描默认到类路径下的haha文件夹下面去找，所以原先springboot指定的静态资源目录都失效了。

#### webjar

自动映射 /[webjars](http://localhost:8080/webjars/jquery/3.5.1/jquery.js)/**。也就是说只要导入了webjars里面的依赖，比如jquery,bootstrap等，如果你要访问这里面的文件也就是你要在Html文件中引入javascript(jquery)，你可以使用/web/xxx的方式来加载静态资源，也就不需要自己添加到resources里面了。

webjars的网站：https://www.webjars.org/

```xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.5.1</version>
</dependency>
```

访问地址：[http://localhost:8080/webjars/**jquery/3.5.1/jquery.js**](http://localhost:8080/webjars/jquery/3.5.1/jquery.js)   后面地址要按照依赖里面的包路径

## 注意事项

1. javabean的注入顺序

   ![](https://raw.githubusercontent.com/Lounwb/imgbed-picgo-repo/master/blogimg/202301192132633.png)

## 开发技巧

### Lombok

lombok可以简化javabean的书写，减少书写有参无参构造器，setter and getter、toString等等。lombok在编译阶段生效，会根据java类中的属性自动生成相应的方法。

使用方式：

1.引入lombok

springboot已经管理了lombok的版本，所以我们只需要引入lombok即可

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

并且需要在IDEA的plugins里面搜索并且下载lombok插件

2.在javabean上标注注解

#### @Data

相当于setter and getter

@ToString

相当于toString

@AllArgsConstructor

包含所有参数的构造器，如果想使用部分参数的构造器需要自行编写。

@NoArgsConstructor

无参构造器

#### @Slf4j

日志插件

使用方法：log.xxx

### dev-tools

自动重启，并不是热部署。 使用ctrl+F9重构项目生效

1.导入插件

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

### Spring initializer

项目初始化向导

### spring-boot-configuration-processor

解决了在yml/yaml文件中编写没有提示的问题

使用方法：

在maven中导入依赖，重新启动服务器即可

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

加入配置加载器后 因为这个功能只是在编码阶段提示作用，所以我们打包的时候不需要这个插件，加入这个插件还会使得服务器启动缓慢，因此需要在pom文件中加入这段代码

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <!--这一段代码 -->
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-configuration-processor</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```



## 目录结构

![](https://raw.githubusercontent.com/Lounwb/imgbed-picgo-repo/master/blogimg/202301232053365.png)
