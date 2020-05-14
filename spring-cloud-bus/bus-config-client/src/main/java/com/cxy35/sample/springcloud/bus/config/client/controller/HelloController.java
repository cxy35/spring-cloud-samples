package com.cxy35.sample.springcloud.bus.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author cxy35
 * @Date 2020/5/10 15:53
 */
@RestController
@RefreshScope
public class HelloController {
    @Value("${cxy35}")
    String cxy35;

    @GetMapping("/hello")
    public String hello(){
        return cxy35;
    }
}
