package com.cxy35.sample.springcloud.consul.client.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author cxy35
 * @Date 2020/4/16 10:18
 */
@RestController
public class ConsumerController {
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/testConsul")
    public String testConsul() {
        ServiceInstance choose = loadBalancerClient.choose("provider");
        System.out.println("服务地址：" + choose.getUri());
        System.out.println("服务名称：" + choose.getServiceId());
        String s = restTemplate.getForObject(choose.getUri() + "/hello", String.class);
        return s;
    }
}
