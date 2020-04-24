package com.cxy35.sample.springcloud.openfeign.client.consumer.service;

import com.cxy35.sample.springcloud.common.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

// @FeignClient("openfeign-client-provider")
public interface ConsumerService {

    @GetMapping("/hello")
    String hello(); // 这里的方法名无所谓，随意取

    @GetMapping("/hello2")
    String hello2(@RequestParam("name") String name);

    @PostMapping("/user")
    User addUser(@RequestBody User user);

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable("id") Integer id);

    @GetMapping("/user")
    void getUserByName(@RequestHeader("name") String name);
}
