package com.eureka.springcloud.cloudservice;

import com.eureka.springcloud.fallback.HystrixFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT8004", fallback = HystrixFeignFallBack.class)
public interface HystrixFeignService {

    /**
     * 服务降级 全局配置
     * @param id
     * @return
     */
    @GetMapping(value = "/cloud/payment/hystrix/ok/{id}")
    String ok(@PathVariable("id") Integer id);

    /**
     * 服务降级 手动一对一配置
     * @param id
     * @return
     */
    @GetMapping(value = "/cloud/payment/hystrix/timeout/{id}")
    String timeout(@PathVariable("id") Integer id);

    /**
     * 服务降级 openfeign方式
     * @param id
     * @return
     */
    @GetMapping(value = "/cloud/payment/hystrix/openfeign/{id}")
    String openFeign(@PathVariable("id") Integer id);

    /**
     * 服务熔断 测试服务接口
     * @param id
     * @return
     */
    @GetMapping(value = "/cloud/payment/hystrix/circuitbreaker/{id}")
    String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
