package com.eureka.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/cloud/config/client/getConfigInfo")
    public String getConfigInfo(){
        return "serverPort：" + serverPort + " \n configInfo：" + configInfo;
    }
}
