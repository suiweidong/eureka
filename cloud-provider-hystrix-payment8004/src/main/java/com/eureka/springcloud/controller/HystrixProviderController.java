package com.eureka.springcloud.controller;

import com.eureka.springcloud.service.HystrixProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HystrixProviderController {

    @Resource
    private HystrixProviderService hystrixProviderService;

    /**
     * 全局配置 服务降级
     * @param id
     * @return
     */
    @GetMapping(value = "/cloud/payment/hystrix/ok/{id}")
    public String ok(@PathVariable("id") Integer id){
        return hystrixProviderService.ok(id);
    }

    /**
     * 每个方法自己单独配置 服务降级
     * @param id
     * @return
     */
    @GetMapping(value = "/cloud/payment/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") Integer id){
        return hystrixProviderService.timeout(id);
    }

    /**
     * openfeign方式 实现服务降级
     * @param id
     * @return
     */
    @GetMapping(value = "/cloud/payment/hystrix/openfeign/{id}")
    public String openFeign(@PathVariable("id") Integer id){
        return this.hystrixProviderService.openFeign(id);
    }



    //-------------------------------------------------------------------------------

    /**
     * 服务熔断 专用测试服务类
     * @param id
     * @return
     */
    @GetMapping(value = "/cloud/payment/hystrix/circuitbreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        return this.hystrixProviderService.circuitBreaker(id);
    }
}
