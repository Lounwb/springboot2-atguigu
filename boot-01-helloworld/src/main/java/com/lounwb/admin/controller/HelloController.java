package com.lounwb.admin.controller;

import com.lounwb.admin.bean.Car;
import com.lounwb.admin.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
public class HelloController {

    @Autowired
    Car car;
    @Autowired
    Person person;
    @RequestMapping("/person")
    public Person person(){
        return person;
    }
    @RequestMapping("/car")
    public Car car(){
        return car;
    }
    @RequestMapping("/hello")
    public String handle01(@RequestParam("name") String name){
        log.info(name + "访问/hello");
        return "Hello,Spring boot2! " + name;
    }

}
