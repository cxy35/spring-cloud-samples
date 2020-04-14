package com.cxy35.sample.springcloud.eureka.client.provider.controller;

import com.cxy35.sample.springcloud.common.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用于测试 RestTemplate
 */
@Controller
public class ProviderController2 {
    @GetMapping("/user")
    @ResponseBody
    public User findUser(String username) {
        User user = new User();
        user.setId(12);
        user.setUsername(username);
        user.setPassword("123456");
        return user;
    }

    @PostMapping("/user")
    @ResponseBody
    public User addUser(User user) {
        return user;
    }

    @PostMapping("/user2")
    @ResponseBody
    public User addUser2(@RequestBody User user) {
        return user;
    }

    @PostMapping("/register")
    public String register(User user) {
        return "redirect:http://provider/loginPage?username=" + user.getUsername();
    }

    @GetMapping("/loginPage")
    @ResponseBody
    public String loginPage(String username) {
        return "loginPage: " + username;
    }

    @PutMapping("/user")
    @ResponseBody
    public void updateUser(User user) {
        System.out.println("updateUser: " + user);
    }

    @PutMapping("/user2")
    @ResponseBody
    public void updateUser2(@RequestBody User user) {
        System.out.println("updateUser2: " + user);
    }

    @DeleteMapping("/user")
    @ResponseBody
    public void deleteUser(Integer id) {
        System.out.println("deleteUser: " + id);
    }

    @DeleteMapping("/user2/{id}")
    @ResponseBody
    public void deleteUser2(@PathVariable Integer id) {
        System.out.println("deleteUser2: " + id);
    }
}
