package com.eureka.springcloud.service.serviceimpl;

import com.eureka.springcloud.cloudservice.HystrixFeignService;
import com.eureka.springcloud.service.HystrixConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "globalFallBack", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
})
public class HystrixConsumerServiceImpl implements HystrixConsumerService {

    @Resource
    private HystrixFeignService hystrixFeignService;

    /**
     * 服务端
     *       之
     *          全局配置
     * @param id
     * @return
     */
    @Override
    @HystrixCommand
    public String ok(Integer id) {
        return this.hystrixFeignService.ok(id);
    }
    public String globalFallBack()
    {
        return "我是80消费端全局服务降级方案：" + Thread.currentThread().getName();
    }

    /**
     * 消费端
     *      之
     *          一个服务一个单独写降级方案
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "timeout_handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")  //3秒钟以内就是正常的业务逻辑
    })
    @Override
    public String timeout(Integer id) {
        // 异常测试
//        long a = 5/0;

        // 超时测试
        long number = 5;
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.hystrixFeignService.timeout(id);
    }
    public String timeout_handler(Integer id){
        return "我是80消费端降级方案：" + Thread.currentThread().getName();
    }

    /**
     * openFeign方式配置服务降级
     * @param id
     * @return
     */
    @Override
    public String openFeign(Integer id) {
        return this.hystrixFeignService.openFeign(id);
    }

    //--------------------------------------------------------------------------------------------------------------------

    /**
     * 服务熔断 java代码方式实现
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "circuitBreakerJava_handler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),      //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),      //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),    //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),    //失败率达到多少后跳闸
    })
    public String circuitBreakerJava(Integer id){
        return this.hystrixFeignService.paymentCircuitBreaker(id);
    }
    public String circuitBreakerJava_handler(Integer id) {
        return "服务熔断测试结果：执行降级：" + Thread.currentThread().getName();
    }


    /**
     * 服务熔断  yml方式实现
     * @param id
     * @return
     */
    @Override
    public String circuitBreakerYml(Integer id) {
        return this.hystrixFeignService.paymentCircuitBreaker(id);
    }
}
