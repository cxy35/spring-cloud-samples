package com.cxy35.sample.springcloud.eureka.client.consumer.controller;

import com.cxy35.sample.springcloud.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用于测试 RestTemplate
 */
@RestController
public class ConsumerController2 {
    @Autowired
    @Qualifier("restTemplateLoadBalanced")
    RestTemplate restTemplateLoadBalanced;

    @GetMapping("/testGet")
    public void testGet() {
        User user = restTemplateLoadBalanced.getForObject("http://provider/user?username={1}", User.class, "cxy35");
        System.out.println(user);

        ResponseEntity<User> responseEntity = restTemplateLoadBalanced.getForEntity("http://provider/user?username={1}", User.class, "cxy35");
        user = responseEntity.getBody();
        System.out.println("body:" + user);
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("HttpStatus:" + statusCode);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        System.out.println("statusCodeValue:" + statusCodeValue);
        HttpHeaders headers = responseEntity.getHeaders();
        Set<String> keySet = headers.keySet();
        System.out.println("--------------header-----------");
        for (String s : keySet) {
            System.out.println(s + ":" + headers.get(s));
        }
    }

    @GetMapping("/testGet2")
    public void testGet2() throws UnsupportedEncodingException {
        // 传参方式：字符串
        User user = restTemplateLoadBalanced.getForObject("http://provider/user?username={1}", User.class, "cxy35");
        System.out.println(user);

        // 传参方式：Map
        Map<String, Object> map = new HashMap<>();
        map.put("username", "zhangsan");
        user = restTemplateLoadBalanced.getForObject("http://provider/user?username={username}", User.class, map);
        System.out.println(user);

        // 传参方式：URI
        String url = "http://provider/user?username=" + URLEncoder.encode("张三", "UTF-8");
        URI uri = URI.create(url);
        user = restTemplateLoadBalanced.getForObject(uri, User.class);
        System.out.println(user);
    }

    @GetMapping("/testPost")
    public void testPost() {
        // 传参方式：key/value
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "cxy35");
        map.add("password", "123");
        map.add("id", 99);
        User user = restTemplateLoadBalanced.postForObject("http://provider/user", map, User.class);
        System.out.println(user);

        // 传参方式：JSON
        user.setId(98);
        user = restTemplateLoadBalanced.postForObject("http://provider/user2", user, User.class);
        System.out.println(user);
    }

    @GetMapping("/testPost2")
    public void testPost2() {
        // postForLocation 实现重定向
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "cxy35");
        map.add("password", "123");
        map.add("id", 99);
        URI uri = restTemplateLoadBalanced.postForLocation("http://provider/register", map);
        System.out.println(uri);

        String s = restTemplateLoadBalanced.getForObject(uri, String.class);
        System.out.println(s);
    }

    @GetMapping("/testPut")
    public void testPut() {
        // 传参方式：key/value
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "cxy35");
        map.add("password", "123");
        map.add("id", 99);
        restTemplateLoadBalanced.put("http://provider/user", map);

        // 传参方式：JSON
        User user = new User();
        user.setId(98);
        user.setUsername("zhangsan");
        user.setPassword("456");
        restTemplateLoadBalanced.put("http://provider/user2", user);
    }

    @GetMapping("/testDelete")
    public void testDelete() {
        restTemplateLoadBalanced.delete("http://provider/user?id={1}", 99);

        restTemplateLoadBalanced.delete("http://provider/user2/{1}", 99);
    }
}
