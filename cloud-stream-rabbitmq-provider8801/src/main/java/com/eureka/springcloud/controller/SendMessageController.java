package com.eureka.springcloud.controller;

import com.eureka.springcloud.service.impl.MessageSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {

    @Resource
    private MessageSender messageSender;

    @GetMapping(value = "/cloud/provider/sendMessage/send")
    public void sendMessage(){
        this.messageSender.send();
    }

}
