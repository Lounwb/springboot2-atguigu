package com.lounwb.admin;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@DisplayName("junit5测试")
public class Junit5Test {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @DisplayName("测试displayname注解")
    @Test
    void testDisplayName(){
        System.out.println("test1运行");
        System.out.println(jdbcTemplate);
    }
    @Disabled
    @DisplayName("测试2")
    @Test
    void test2(){
        System.out.println("test2运行");
    }
    @RepeatedTest(5)
    @Test
    void test3(){
        System.out.println("重复测试");
    }
    @Disabled
    @DisplayName("超时测试")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void testTimeout() throws InterruptedException {
        Thread.sleep(600);
    }

    @BeforeEach
    void testBeforeEach(){
        System.out.println("每个测试之前运行");
    }

    @AfterEach
    void testAfterEach(){
        System.out.println("每个测试之后运行");
    }
    @BeforeAll
    static void testBeforeAll(){
        System.out.println("所有测试开始了...");
    }
    @AfterAll
    static void testAfterAll(){
        System.out.println("所有测试结束了...");
    }
    @DisplayName("测试简单断言")
    @Test
    void testSimpleAssertions(){
        int sum = sum(1,2);
        assertEquals(3, sum);
    }
    int sum(int x, int y){
        return x+y;
    }
}
