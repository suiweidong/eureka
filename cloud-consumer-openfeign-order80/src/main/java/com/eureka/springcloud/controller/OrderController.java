package com.eureka.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eureka.springcloud.cloudservice.PaymentService;
import com.eureka.springcloud.entities.Payment;
import com.eureka.springcloud.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/cloud/payment/create")
    public CommonResponse<Payment> create(@RequestBody Payment payment) {
        log.info("=================consumer-create-param: " + JSON.toJSONString(payment, SerializerFeature.PrettyFormat));
        return paymentService.create(payment);
    }

    @GetMapping("/consumer/cloud/payment/get/{id}")
    public CommonResponse<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("start");
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/consumer/cloud/payment/lb")
    public CommonResponse<String> lb(){
        return paymentService.lb();
    }
}
