package com.eureka.springcloud.service.serviceimpl;

import cn.hutool.core.util.IdUtil;
import com.eureka.springcloud.service.HystrixProviderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "globalRallBack", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
})
public class HystrixProviderServiceImpl implements HystrixProviderService {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 服务端
     * 之
     * 全局配置
     *
     * @param id
     * @return
     */
    @Override
    @HystrixCommand
    public String ok(Integer id) {
        // 异常测试
//        long a = 5/0;

        // 超时测试
        long number = 5;
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "端口号：" + serverPort + "- 线程：" + Thread.currentThread().getName() + "- 请求ok";
    }

    public String globalRallBack() {
        return "我是8004服务端全局服务降级方案：" + Thread.currentThread().getName();
    }

    /**
     * 服务端
     * 之
     * 一个服务一个单独写服务降级方案
     *
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "timeout_handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")  //3秒钟以内就是正常的业务逻辑
    })
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

        return "端口号：" + serverPort + "- 线程：" + Thread.currentThread().getName() + "- 请求timeout，睡眠时间：" + number;
    }

    public String timeout_handler(Integer id) {
        return "我是8004服务端timeout服务降级方案：" + Thread.currentThread().getName();
    }


    /**
     * hystrix降级在消费端openFeign中配置，测试专用服务
     *
     * @param id
     * @return
     */
    @Override
    public String openFeign(Integer id) {

        // 超时测试
        long number = 5;
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "端口号：" + serverPort + "- 线程：" + Thread.currentThread().getName() + "-openFeign";
    }

    //-------------------------------------------------------------------------------------------------------------------


    /**
     * 服务熔断 Java编写测试
     *
     * @param id
     * @return
     */
    @Override
    public String circuitBreaker(Integer id) {
        if (id < 0){
            throw new RuntimeException("id不能为负数");
        }

        String uuid = IdUtil.simpleUUID();
        return "服务熔断测试成功：uuid：" + uuid;
    }
}
