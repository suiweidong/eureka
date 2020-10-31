package com.eureka.springcloud.controller;

import com.eureka.springcloud.service.HystrixConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HystrixConsumerController {

    @Resource
    private HystrixConsumerService hystrixConsumerService;

    /**
     * 服务降级  全局配置
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/cloud/payment/hystrix/ok/{id}")
    public String ok(@PathVariable("id") Integer id){
        return this.hystrixConsumerService.ok(id);
    }


    /**
     * 服务降级  手动一对一配置
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/cloud/payment/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") Integer id){
        return this.hystrixConsumerService.timeout(id);
    }

    /**
     * 服务降级 openfeign方式
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/cloud/payment/hystrix/openfeign/{id}")
    public String openFeign(@PathVariable("id") Integer id){
        return this.hystrixConsumerService.openFeign(id);
    }

    //---------------------------------------------------------------------------------------------------------------

    /**
     * 服务熔断 java代码配置
     *      如果使用该方式，必须关闭openFeign配置Hystrix方式
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/cloud/payment/hystrix/circuitBreakerJava/{id}")
    public String circuitBreakerJava(@PathVariable("id") Integer id){
        return this.hystrixConsumerService.circuitBreakerJava(id);
    }

    /**
     * 服务熔断 yml方式实现
     *      如果使用该方式，必须使用openFeign配置Hystrix方式
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/cloud/payment/hystrix/circuitBreakerYml/{id}")
    public String circuitBreakerYml(@PathVariable("id") Integer id){
        return this.hystrixConsumerService.circuitBreakerYml(id);
    }

}
