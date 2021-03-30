package com.cxy35.sample.springcloud.eureka.client.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @Author cxy35
 * @Date 2020/4/13 13:00
 */
@RestController
public class ConsumerController {
    // 通过 HttpURLConnection（ JDK 自带，类似于 HttpClient ） 调用 provider 中的接口，写死 provider 的地址
    @GetMapping("/testByHttpURLConnection")
    public String testByHttpURLConnection() {
        HttpURLConnection con = null;
        try {
            URL url = new URL("http://127.0.0.1:1113/hello");
            con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @Autowired
    DiscoveryClient discoveryClient;

    // 通过 HttpURLConnection（ JDK 自带，类似于 HttpClient ） 调用 provider 中的接口，通过 DiscoveryClient 动态获取 provider 的地址
    @GetMapping("/testByHttpURLConnection2")
    public String testByHttpURLConnection2() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        StringBuffer sb = new StringBuffer();
        sb.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/hello");
        HttpURLConnection con = null;
        try {
            URL url = new URL(sb.toString());
            con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    // 通过 HttpURLConnection（ JDK 自带，类似于 HttpClient ） 调用 provider 中的接口，通过 DiscoveryClient 动态获取 provider 的地址，并手动实现客户端线性负载均衡
    int count = 0;
    @GetMapping("/testByHttpURLConnection3")
    public String testByHttpURLConnection3() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get((count++) % list.size());
        String host = instance.getHost();
        int port = instance.getPort();
        StringBuffer sb = new StringBuffer();
        sb.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/hello");
        HttpURLConnection con = null;
        try {
            URL url = new URL(sb.toString());
            con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    // 【推荐】通过 RestTemplate 调用 provider 中的接口，通过 DiscoveryClient 动态获取 provider 的地址
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @GetMapping("/testByRestTemplate")
    public String testByRestTemplate() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        StringBuffer sb = new StringBuffer();
        sb.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/hello");
        String s = restTemplate.getForObject(sb.toString(), String.class);
        return s;
    }

    // 【推荐】通过 RestTemplate 调用 provider 中的接口，通过 DiscoveryClient 动态获取 provider 的地址，并使用 @LoadBalanced 实现客户端负载均衡
    @Autowired
    @Qualifier("restTemplateLoadBalanced")
    RestTemplate restTemplateLoadBalanced;

    @GetMapping("/testByRestTemplate2")
    public String testByRestTemplate2() {
        return restTemplateLoadBalanced.getForObject("http://provider/hello", String.class);
    }
}
