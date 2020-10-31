package com.eureka.springcloud.service;

public interface HystrixConsumerService {

    String ok(Integer id);

    String timeout(Integer id);

    String openFeign(Integer id);

    String circuitBreakerJava(Integer id);

    String circuitBreakerYml(Integer id);
}
