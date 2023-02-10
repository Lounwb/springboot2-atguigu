package com.lounwb.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("com.lounwb.admin.mapper")
@ServletComponentScan
@SpringBootApplication
public class Boot05AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot05AdminApplication.class, args);
    }

}
