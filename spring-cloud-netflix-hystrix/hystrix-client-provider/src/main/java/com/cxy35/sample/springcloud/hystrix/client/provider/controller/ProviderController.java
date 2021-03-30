package com.cxy35.sample.springcloud.hystrix.client.provider.controller;

import com.cxy35.sample.springcloud.common.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author cxy35
 * @Date 2020/4/18 12:55
 */
@RestController
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

    // 假设 consumer 传过来的多个 id 的格式是 1,2,3,4....
    @GetMapping("/user/{ids}")
    public List<User> getUserByIds(@PathVariable String ids) {
        System.out.println(ids);
        String[] split = ids.split(",");
        List<User> users = new ArrayList<>();
        for (String s : split) {
            User u = new User();
            u.setId(Integer.parseInt(s));
            users.add(u);
        }
        return users;
    }
}
