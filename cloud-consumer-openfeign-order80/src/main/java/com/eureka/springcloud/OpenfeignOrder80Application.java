package com.eureka.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
//@ComponentScan(value = {"com.eureka.springcloud.cloudservice","com.eureka.springcloud"})
//@ComponentScan({"com.eureka.springcloud.cloudservice","com.eureka.springcloud"})
public class OpenfeignOrder80Application {

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignOrder80Application.class, args);
    }

}
