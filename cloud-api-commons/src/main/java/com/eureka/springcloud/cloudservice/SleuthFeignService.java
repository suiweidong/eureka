package com.eureka.springcloud.cloudservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "provider-sleuth-payment8005")
public interface SleuthFeignService {

    @GetMapping("/cloud/sleuth")
    String sleuth();
}
