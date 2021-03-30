package com.cxy35.sample.springcloud.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author cxy35
 * @Date 2020/5/23 15:52
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello sentinel";
    }
}
