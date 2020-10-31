package com.eureka.springcloud.cloudservice;

import com.eureka.springcloud.entities.Payment;
import com.eureka.springcloud.utils.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "CLOUD-PROVIDER-SERVICE")
public interface PaymentService {

    @PostMapping("/cloud/payment/create")
    CommonResponse<Payment> create(@RequestBody Payment payment);

    @GetMapping("/cloud/payment/get/{id}")
    CommonResponse<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/cloud/payment/lb")
    CommonResponse<String> lb();
}
