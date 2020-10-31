package com.eureka.springcloud.fallback;

import com.eureka.springcloud.cloudservice.HystrixFeignService;
import org.springframework.stereotype.Component;

@Component
public class HystrixFeignFallBack implements HystrixFeignService {

    /**
     * 服务降级 openFeign方式配置
     * @param id
     * @return
     */
    @Override
    public String openFeign(Integer id) {
        return "消费端openFeign方式，服务降级";
    }

    /**
     * 服务降级 全局配置
     * @param id
     * @return
     */
    @Override
    public String ok(Integer id) {
        return null;
    }

    /**
     * 服务降级 手动一对一配置
     * @param id
     * @return
     */
    @Override
    public String timeout(Integer id) {
        return null;
    }

    // ------------------------------------------------------

    /**
     * 服务熔断 上游服务接口 fallback
     * @param id
     * @return
     */
    @Override
    public String paymentCircuitBreaker(Integer id) {
        return "上游服务熔断，方法调用失败，执行服务降级";
    }

}
