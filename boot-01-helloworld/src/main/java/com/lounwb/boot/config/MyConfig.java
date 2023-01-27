package com.lounwb.boot.config;

import com.lounwb.boot.bean.Pet;
import com.lounwb.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//@Import({User.class, DBHelper.class})
@ImportResource("classpath:beans.xml")
@Configuration
public class MyConfig {
    @Bean("tom")
    public Pet tomcat(){
        return new Pet("tomcat");
    }
    @ConditionalOnBean(name = "tom")
    @Bean
    public User user01(){
        return new User("zhangsan", 18);
    }

}