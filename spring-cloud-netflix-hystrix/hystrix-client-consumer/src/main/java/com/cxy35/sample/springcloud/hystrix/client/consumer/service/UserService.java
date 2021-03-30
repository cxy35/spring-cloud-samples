package com.cxy35.sample.springcloud.hystrix.client.consumer.service;

import com.cxy35.sample.springcloud.common.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

// 请求命令方式，实现请求合并
@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    public List<User> getUsersByIds(List<Integer> ids) {
        User[] users = restTemplate.getForObject("http://hystrix-client-provider/user/{1}", User[].class, StringUtils.join(ids, ","));
        return Arrays.asList(users);
    }
}
