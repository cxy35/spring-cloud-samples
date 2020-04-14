package com.cxy35.sample.springcloud.eureka.client.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author cxy35
 * @Date 2020/4/13 12:55
 */
@RestController
public class ProviderController {
    @Value("${server.port}")
    Integer port; // 支持启动多个实例，做负载均衡，用端口区分

    @GetMapping("/hello")
    public String hello() {
        return "hello cxy35: " + port;
    }
}
