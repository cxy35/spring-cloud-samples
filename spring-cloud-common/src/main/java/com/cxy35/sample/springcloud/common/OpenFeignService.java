package com.cxy35.sample.springcloud.common;

import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * @Author cxy35
 * @Date 2020/4/24 16:01
 */
public interface OpenFeignService {
    @GetMapping("/hello")
    String hello(); // 这里的方法名无所谓，随意取

    @GetMapping("/hello2")
    String hello2(@RequestParam("name") String name);

    @PostMapping("/user")
    User addUser(@RequestBody User user);

    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable("id") Integer id);

    @GetMapping("/user")
    void getUserByName(@RequestHeader("name") String name) throws UnsupportedEncodingException;
}
