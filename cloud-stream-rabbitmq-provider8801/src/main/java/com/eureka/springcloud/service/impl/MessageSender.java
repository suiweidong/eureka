package com.eureka.springcloud.service.impl;

import com.eureka.springcloud.service.IMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

//定义消息推送管道
@EnableBinding(Source.class)
@Slf4j
public class MessageSender implements IMessageSender {

    //定义消息发送管道
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String uuid = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(uuid).build());
        log.info("调用uuid：" + uuid);
        return null;
    }
}
