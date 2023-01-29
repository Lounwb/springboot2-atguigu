package com.lounwb.admin.config;

import com.lounwb.admin.bean.Pet;
import com.lounwb.admin.bean.User;
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