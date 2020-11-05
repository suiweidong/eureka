package com.eureka.springcloud.controller;

import com.eureka.springcloud.cloudservice.SleuthFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SleuthConsumerController {

    @Resource
    private SleuthFeignService sleuthFeignService;

    @GetMapping(value = "/cloud/consumer/sleuth")
    public String sleuth(){
        return this.sleuthFeignService.sleuth();
    }

}
