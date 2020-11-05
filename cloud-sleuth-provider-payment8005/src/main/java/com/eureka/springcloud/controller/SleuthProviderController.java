package com.eureka.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SleuthProviderController {

    @GetMapping("/cloud/sleuth")
    public String sleuth(){
        return "sleuth-provider测试";
    }
}
