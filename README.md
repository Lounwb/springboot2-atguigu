[![Java CI](https://img.shields.io/github/actions/workflow/status/alibaba/fastjson2/ci.yaml?branch=main&logo=github&logoColor=white)](https://github.com/alibaba/fastjson2/actions/workflows/ci.yaml)

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

### 业务层注解Service

#### @Service

MVC架构模式

```
src/
 +- main/
     +- java/
        + service/
           + impl/
           |    + <UserServiceImpl>(java)
           + <UserService>(Interface)
```

需要在业务层实现类上标注@Service，如果整合MybatisPlus需要extends ServiceImpl<UserMapper, User>

```java
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
```

而接口UserService需要继承IService<User>，表明这个业务层是对User对象操作

```java
public interface UserService extends IService<User> {

}
```

### DAO层注解Mapper

#### @Mapper

如果使用Mybatis需要在Mapper上标注@Mapper，表明这是一个dao

如果使用Myabtis-Plus，则需要继承BaseMapper<bean>，表明对Bean进行操作

```java
public interface UserMapper extends BaseMapper<User> {

}
```

整合mybatis-plus不需要写sql，都是逆向生成。

### 控制层注解Controller

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



### 配置类注解Config

#### @Configuration

​	告诉SpringBoot这是一个配置类，等同于一个xml配置文件，配置类本身也是SpringBoot的一个组件，由代理类生成。配置类里面的Bean默认是单实例的。如果你不想让SpringBoot去管理，让他不是单实例的，可以使用@Configuration(proxyBeanMethods = "false")   //代理bean方法默认为true。

​	原来proxyBeanMethods= "true"表明bean由springboot容器管理，拿的时候从容器中拿，容器中只有一个实例，所以是单实例模式。而如果设置proxyBeanMethods= “false”，每次调用方法都会new一个新对象，所以不是单实例。

1. 向容器中添加组件

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
       <bean id="user01" class="com.lounwb.admin.bean.User">
           <property name="name" value="zhangsan"></property>
           <property name="age" value="18"></property>
       </bean>
       <bean id="cat" class="com.lounwb.admin.bean.Pet">
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

### 请求参数注解

#### @RequestParam

标注在形参上面

```java
@GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
//        List<User> users = Arrays.asList(new User("zhangsan", "123456"),
//                new User("lisi", "123444"),
//                new User("wangwu", "aaaa"),
//                new User("zhaoliu", "1234bbb"));
//        model.addAttribute("users", users);
       // List<User> list = userService.list();
        //如何进行分页查询
        Page<User> page = new Page<>(pn, 2);
        //分页查询对象
        Page<User> userPage = userService.page(page, null);

        model.addAttribute("page", userPage);

        return "table/dynamic_table";
    }
```

#### @PathVariable

从请求路径中动态获取参数

例如请求为localhost:8080/car/88/owner/zhangsan

如果想拿到88和zhangsan可以搭配@GetMapping使用

```java
@GetMapping("/user/delete/{id}")
public String deleteUser(@PathVariable("id") Long id,
                         @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                         RedirectAttributes ra){
    //RedirectAttributes可以向请求中添加参数.
    userService.removeById(id);
    ra.addAttribute("pn", pn);

    return "redirect:/dynamic_table";
}
```

#### @RequestHeader

```java
@RequestHeader("User-Agent") String userAgent,
@RequestHeader Map<String, String> headers
```

#### 其他参数注解

@CookieValue、@RequestBody

## web开发

### web原生组件注入（Servlet,Filter,Listener）

**1.在主应用类上添加注释@ServletComponentScan(basePackages="servlet的扫描路径")**

**2.编写原生Servlet**

第一种方式：使用servlet3.x的注解形式@WebServlet(urlPatterns={"xxx"}) 或者***使用第二种方式见3.***

原生Servlet的效果 是直接响应，不会经过Spring的拦截器。

```java
@WebServlet("/my")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("welcome to my servlet");
    }
}

```

**3.向容器中注入Servlet（使用RegistrationBean）**

```java
@Configuration
public class MyRegistConfig {

    @Bean
    public ServletRegistrationBean myServlet(){
        MyServlet myServlet = new MyServlet();

        return new ServletRegistrationBean(myServlet,"/my","/my02");
    }


    @Bean
    public FilterRegistrationBean myFilter(){

        MyFilter myFilter = new MyFilter();
//        return new FilterRegistrationBean(myFilter,myServlet());
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my","/css/*"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        MySwervletContextListener mySwervletContextListener = new MySwervletContextListener();
        return new ServletListenerRegistrationBean(mySwervletContextListener);
    }
}
```

### 数据访问

**1.导入JDBC场景**

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jdbc</artifactId>
        </dependency>
```

默认使用的数据源是**HikariDataSource**

因为官方不知道你要用什么数据库，所以需要手动导入驱动。

```xml
默认版本：<mysql.version>8.0.22</mysql.version>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
<!--            <version>5.1.49</version>-->
        </dependency>
想要修改版本
1、直接依赖引入具体版本（maven的就近依赖原则）
2、重新声明版本（maven的属性的就近优先原则）
    <properties>
        <java.version>1.8</java.version>
        <mysql.version>5.1.49</mysql.version>
    </properties>
```

**2.修改配置项**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/db_account
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver
```

**3.测试**

```java
@Slf4j
@SpringBootTest
class Boot05WebAdminApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    void contextLoads() {

//        jdbcTemplate.queryForObject("select * from account_tbl")
//        jdbcTemplate.queryForList("select * from account_tbl",)
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account_tbl", Long.class);
        log.info("记录总数：{}",aLong);
    }

}
```

### 使用druid数据源

**1.手动方式**

整合第三方技术的两种方式

1. 自定义
2. starter

**druid官方github地址：**   https://github.com/alibaba/druid

具体如何在springboot中手动整合druid见wiki。

**2.使用官方starter**

### 整合MyBatis

```xml
<!--只需要一个mybatis stater即可 -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.4</version>
</dependency>
```

#### 配置模式

**1.配置模式**

```yaml
# 配置mybatis规则
mybatis:
  config-location: classpath:mybatis/mybatis-config.xml  #全局配置文件位置
  mapper-locations: classpath:mybatis/mapper/*.xml  #sql映射文件位置
```

```xml
<!-- myabtis-config.xml文件 -->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="${driver}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
    <mapper resource="org/mybatis/example/BlogMapper.xml"/>
  </mappers>
</configuration>
```

```xml
<!--xxx.xml文件 -->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.admin.mapper.AccountMapper">
<!--    public Account getAcct(Long id); -->
    <select id="getAcct" resultType="com.atguigu.admin.bean.Account">
        select * from  account_tbl where  id=#{id}
    </select>
</mapper>
```

使用方式：

1. 导入mybatis官方starter

2. 编写mapper接口。标准@Mapper注解

   ```java
   @Mapper
   public interface AccountMapper {
       public Account getActById(Integer id);
   }
   ```

3. 编写sql映射文件并绑定mapper接口

4. 在application.yaml中指定Mapper配置文件的位置，以及指定全局配置文件的信息 （建议；**配置在mybatis.configuration**）

#### 注解模式

使用方式：

1. 引入mybatis-starter

2. **配置application.yaml中，指定mapper-location位置即可**

   ```yaml
   mybatis:
   #  config-location: classpath:mybatis/mybatis-config.xml
     mapper-locations: classpath:mybatis/mapper/*.xml
   #	java驼峰命名转数据库中字段命名方式 userId->user_id
     configuration:
       map-underscore-to-camel-case: true
   ```

3. 编写Mapper接口并标注@Mapper注解

   ```java
   @Mapper
   public interface CityMapper {
       //不需要写
       @Select("select * from city where id=#{id}")
       public City getById(Integer id);
   
       public void insert(City city);
   }
   ```

4. 简单方法直接注解方式

5. 复杂方法编写mapper.xml进行绑定映射

6. *@MapperScan("com.atguigu.admin.mapper") 简化，其他的接口就可以不用标注@Mapper注解*

   写到主应用上

   ```java
   @MapperScan("com.lounwb.admin.mapper")
   @ServletComponentScan
   @SpringBootApplication
   public class Boot05AdminApplication {
   
       public static void main(String[] args) {
           SpringApplication.run(Boot05AdminApplication.class, args);
       }
   
   }
   ```

### 整合Mybatis-Plus

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.1</version>
</dependency>
```

**优点：**

-  只需要我们的Mapper继承 **BaseMapper** 就可以拥有crud能力

使用方式：

1. 引入场景启动器starter

2. 不需要写@Mapper,直接在主程序上@MapperScan("com.lounwb.admin.mapper")批量扫描

3. 在控制层Controller引入业务层对象

   ```java
   @Controller
   public class TableController {
       @Autowired
       UserService userService;
       
       @GetMapping("/user/delete/{id}")
       public String deleteUser(@PathVariable("id") Long id,
                                @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                RedirectAttributes ra){
           //
           userService.removeById(id);
           ra.addAttribute("pn", pn);
   
           return "redirect:/dynamic_table";
       }
       
   }
   ```

4. 编写Service层接口和实现类

   接口继承IService<bean>,实现类继承ServiceImpl<mapper,bean>

5. 在Dao层继承BaseMapper<bean>
6. 在controller中调用service.xxx方法即可，不需要写sql语句

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

#### 转发的使用

```java
@Controller
public class RequestController {
    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg", "转发成功...");
        request.setAttribute("code", 200);
        return "forward:/success";
    }
    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute String msg,
                       @RequestAttribute Integer code,
                       HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        Object msg1 = request.getAttribute("msg");

        map.put("msg", msg);
        map.put("msg1", msg1);
        map.put("code", code);
        return map;
    }
}
```

这也是相当转发，如果刷新/login页面会一直重复提交表单

```java
@GetMapping({"/","/login"})
    public String loginPage(){
        return "login";
    }
@PostMapping("/login")
    public  String main(String username,
                        String password){
        return "main";
    }
// 发送/请求或者/login，跳转到templates/login.index页面
```

#### 重定向



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

**@Data**

相当于setter and getter

@ToString

相当于toString

@AllArgsConstructor

包含所有参数的构造器，如果想使用部分参数的构造器需要自行编写。

@NoArgsConstructor

无参构造器

**@Slf4j**

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

### Thymeleaf

thymeleaf是一个现代化服务端的java模板引擎。**https://www.thymeleaf.org/**

**Thymeleaf** is a modern server-side Java template engine for both web and standalone environments.

Thymeleaf's main goal is to bring elegant *natural templates* to your development workflow — HTML that can be correctly displayed in browsers and also work as static prototypes, allowing for stronger collaboration in development teams.

With modules for Spring Framework, a host of integrations with your favourite tools, and the ability to plug in your own functionality, Thymeleaf is ideal for modern-day HTML5 JVM web development — although there is much more it can do.

| 表达式名字 | 语法   | 用途                               |
| ---------- | ------ | ---------------------------------- |
| 变量取值   | ${...} | 从请求域中取值                     |
| 选择变量   | *{...} | 获取上下文对象值                   |
| 消息       | #{...} | 获取国际化等值                     |
| 链接       | @{...} | 生成链接                           |
| 片段表达式 | ~{...} | jsp:include 作用，引入公共页面片段 |

**引入starter**

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

**在页面上引入thymeleaf**

```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```

**迭代**

```html
<tr th:each="prod : ${prods}">
        <td th:text="${prod.name}">Onions</td>
        <td th:text="${prod.price}">2.41</td>
        <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
</tr>
```

**抽取公共页面**

1.选择片段

使用th:gragment="xxx"

```html
<head th:fragment="commmonheader">
    <!--common-->
    <link href="css/style.css" th:href="@{/css/style.css}" rel="stylesheet">
    <link href="css/style-responsive.css" th:href="@{/css/style-responsive.css}" rel="stylesheet">


    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]-->
    <script src="js/html5shiv.js" th:src="@{/js/html5shiv.js}"></script>
    <script src="js/respond.min.js" th:src="@{/js/respond.min.js}"></script>
    <!--[endif]-->
</head>
```

2.引入片段

```html
<div th:insert="footer :: copy"></div>
<!-- 效果：-->
	<div>
        <footer>
          &copy; 2011 The Good Thymes Virtual Grocery
        </footer>
  </div>
<div th:replace="footer :: copy"></div>
<!-- 效果：-->
    <footer>
        &copy; 2011 The Good Thymes Virtual Grocery
      </footer>
<div th:include="footer :: copy"></div>
<!-- 效果：-->
    <div>
        &copy; 2011 The Good Thymes Virtual Grocery
      </div>
```

```html
<!-- 适合标签上无class，无样式-->
<div th:include="common :: commmonheader"></div>
<!-- 适合标签上有class需要导入样式-->
<div th:replace="common :: leftmenu"></div>
```

### 拦截器

#### HandlerInterceptor 接口

![](https://raw.githubusercontent.com/Lounwb/imgbed-picgo-repo/master/blogimg/202301301527002.png)

添加拦截器的方法

1、编写一个拦截器实现HandlerInterceptor接口

2、将拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors方法）

3、指定拦截规则，拦截那些请求，放行那些请求。

#### WebMvcConfigurer 接口

如果配置类要实现web定制化操作需要实现WebMvcConfigurer接口

```java
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).
                addPathPatterns("/**").    //拦截所有请求包括静态资源
                excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**");   //设置放行那些请求
    }
}

```

### 错误处理

For example, to map `404` to a static HTML file, your directory structure would be as follows:

```none
src/
 +- main/
     +- java/
     |   + <source code>
     +- resources/
         +- public/
             +- error/
             |   +- 404.html
             +- <other public assets>
```

To map all `5xx` errors by using a FreeMarker template, your directory structure would be as follows:

```none
src/
 +- main/
     +- java/
     |   + <source code>
     +- resources/
         +- templates/
             +- error/
             |   +- 5xx.ftlh
             +- <other templates>
```



## 目录结构

![](https://raw.githubusercontent.com/Lounwb/imgbed-picgo-repo/master/blogimg/202301232053365.png)
