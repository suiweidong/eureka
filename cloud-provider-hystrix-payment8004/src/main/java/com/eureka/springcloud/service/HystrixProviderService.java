package com.eureka.springcloud.service;

public interface HystrixProviderService {

    String ok(Integer id);

    String timeout(Integer id);

    String openFeign(Integer id);

    String circuitBreaker(Integer id);
}
