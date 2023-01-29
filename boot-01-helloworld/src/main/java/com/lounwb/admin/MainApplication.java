package com.lounwb.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主程序类
 * SpringBootApplication：这是一个SpringBoot应用
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        /*String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }*/
//        MyConfig bean1 = run.getBean(MyConfig.class);
//        System.out.println(bean1);
//        User user = bean1.user01();
//        User user1 = bean1.user01();
//
//        System.out.println("单实例?" + (user==user1));
//        boolean user01 = run.containsBean("user01");
//        System.out.println("容器中的user01组件:"+user01);
//        boolean tom = run.containsBean("tom");
//        System.out.println(tom);
        boolean haha = run.containsBean("haha");
        System.out.println("haha:"+haha);
        boolean hehe = run.containsBean("hehe");
        System.out.println("hehe:"+hehe);
    }
}