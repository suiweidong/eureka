package com.eureka.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eureka.springcloud.entities.Payment;
import com.eureka.springcloud.lb.LoadBalancer;
import com.eureka.springcloud.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //原写死 x
    //public static final String PAYMENT_URL = "http://localhost:8001";

    //新使用服务名称
    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping("/consumer/cloud/payment/create")
    public CommonResponse<Payment> create(@RequestBody Payment payment) {
        log.info("=================consumer-create-param: " + JSON.toJSONString(payment, SerializerFeature.PrettyFormat));
        return restTemplate.postForObject(PAYMENT_URL + "/cloud/payment/create", payment, CommonResponse.class);
    }

    @GetMapping("/consumer/cloud/payment/get/{id}")
    public CommonResponse<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/cloud/payment/get/" + id, CommonResponse.class);
    }

    @GetMapping("/consumer/cloud/payment/lb")
    public CommonResponse<String> lb(){
        log.info("start");
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        if (ObjectUtils.isEmpty(instances)){
            return null;
        }

//        ServiceInstance serviceInstance = loadBalancer.instances(instances);
//        URI uri = serviceInstance.getUri();    ip不对，以后研究怎么配置，现在读取的是本机Ip

        return restTemplate.getForObject(PAYMENT_URL + "/cloud/payment/lb", CommonResponse.class);
    }



}
