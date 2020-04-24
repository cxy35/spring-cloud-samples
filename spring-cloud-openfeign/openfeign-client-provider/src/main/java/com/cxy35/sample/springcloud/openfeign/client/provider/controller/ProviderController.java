package com.cxy35.sample.springcloud.openfeign.client.provider.controller;

import com.cxy35.sample.springcloud.common.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

// @RestController
public class ProviderController {
    @Value("${server.port}")
    Integer port; // 支持启动多个实例，做负载均衡，用端口区分

    @GetMapping("/hello")
    public String hello() {
        return "hello cxy35: " + port;
    }

    @GetMapping("/hello2")
    public String hello2(String name) {
        System.out.println(new Date());
        return "hello " + name + ": " + port;
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return user;
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Integer id) {
        System.out.println(id);
    }

    @GetMapping("/user")
    public void getUserByName(@RequestHeader String name) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode(name, "UTF-8"));
    }
}
