package com.eureka.springcloud.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eureka.springcloud.entities.Payment;
import com.eureka.springcloud.enums.CommonCodeEnum;
import com.eureka.springcloud.service.IPaymentService;
import com.eureka.springcloud.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author: swd
 * @since 2020-09-11
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private IPaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/cloud/payment/create")
    public CommonResponse<Payment> create(@RequestBody Payment payment){
        log.info("============create-serverPort："+ serverPort +"-param：\n" + JSON.toJSONString(payment, SerializerFeature.PrettyFormat));

        this.paymentService.create(payment);
        return CommonResponse.success();
    }

    @GetMapping("/cloud/payment/get/{id}")
    public CommonResponse<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("==============getPaymentById-serverPort："+ serverPort +"--param：id = " + id);
        Payment payment = this.paymentService.getPaymentById(id);
        log.info("==============getPaymentById-result：\n" + JSON.toJSONString(payment, SerializerFeature.PrettyFormat));
        if (ObjectUtils.isEmpty(payment)){
            return CommonResponse.fail(CommonCodeEnum.FAIL.getName());
        }

        CommonResponse<Payment> success = CommonResponse.success();
        success.setRecord(payment);
        return success;
    }

    @GetMapping("/cloud/discovery")
    public Object discovery(){
        log.info("==============discovery-getServices-start");
        List<String> services = this.discoveryClient.getServices();
        for (String service: services) {
            log.info("===============service：" + service);
        }

        log.info("==============discovery-getInstances-start");
        List<ServiceInstance> instances = this.discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        for (ServiceInstance instance: instances) {
            log.info("===============instace：" + instance.getHost());
            log.info("===============instace：" + instance.getInstanceId());
            log.info("===============instace：" + instance.getServiceId());
            log.info("===============instace：" + instance.getPort());
            log.info("===============instace：" + instance.getUri());
            log.info("============================================");
        }

        return this.discoveryClient;
    }

    @GetMapping("/cloud/payment/lb")
    public CommonResponse<String> lb(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return CommonResponse.success(serverPort);
    }



}
