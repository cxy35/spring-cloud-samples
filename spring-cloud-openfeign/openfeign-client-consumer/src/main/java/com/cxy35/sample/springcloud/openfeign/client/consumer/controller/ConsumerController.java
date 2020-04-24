package com.cxy35.sample.springcloud.openfeign.client.consumer.controller;

import com.cxy35.sample.springcloud.common.User;
import com.cxy35.sample.springcloud.openfeign.client.consumer.service.ConsumerService;
import com.cxy35.sample.springcloud.openfeign.client.consumer.service.ConsumerService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class ConsumerController {
    // @Autowired
    // ConsumerService consumerService;
    @Autowired
    ConsumerService2 consumerService;

    @GetMapping("/hello")
    public String hello() {
        return consumerService.hello();
    }

    @GetMapping("/testOpenFeign")
    public String testOpenFeign() throws UnsupportedEncodingException {
        String s = consumerService.hello();

        String s2 = consumerService.hello2("程序员35");
        System.out.println(s2);

        User user = new User();
        user.setId(1);
        user.setUsername("cxy35");
        user.setPassword("123");

        User u = consumerService.addUser(user);
        System.out.println(u);

        consumerService.deleteUser(1);

        consumerService.getUserByName(URLEncoder.encode("程序员35", "UTF-8"));

        return s;
    }
}
