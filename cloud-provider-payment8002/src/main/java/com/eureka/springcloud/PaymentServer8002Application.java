package com.eureka.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentServer8002Application {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServer8002Application.class, args);
    }

}
