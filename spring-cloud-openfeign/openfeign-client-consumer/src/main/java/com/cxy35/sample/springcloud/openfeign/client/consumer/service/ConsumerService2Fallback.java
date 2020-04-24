package com.cxy35.sample.springcloud.openfeign.client.consumer.service;

import com.cxy35.sample.springcloud.common.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Component
@RequestMapping("/abc") // 防止请求地址重复，可随意定义
public class ConsumerService2Fallback implements ConsumerService2 {
    @Override
    public String hello() {
        return "error-fallback";
    }

    @Override
    public String hello2(String name) {
        return "error2-fallback";
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
    }

    @Override
    public void getUserByName(String name) throws UnsupportedEncodingException {
    }
}
