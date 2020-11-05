package com.eureka.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SleuthProviderPayment8005Application {

    public static void main(String[] args) {
        SpringApplication.run(SleuthProviderPayment8005Application.class, args);
    }

}
