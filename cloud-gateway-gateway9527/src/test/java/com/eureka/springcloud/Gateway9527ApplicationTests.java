package com.eureka.springcloud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

@SpringBootTest
class Gateway9527ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        ZonedDateTime time = ZonedDateTime.now();   //默认时区
        System.out.println(time);
    }

}
