package com.cxy35.sample.springcloud.hystrix.client.consumer.service;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

// 请求命令方式
public class ConsumerService2 extends HystrixCommand<String> {

    RestTemplate restTemplate;
    String name;

    public ConsumerService2(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    public ConsumerService2(Setter setter, RestTemplate restTemplate, String name) {
        super(setter);
        this.restTemplate = restTemplate;
        this.name = name;
    }

    // 服务降级/容错
    /*@Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://hystrix-client-provider/hello", String.class);
    }*/

    // 服务降级/容错，这个方法就是请求失败的回调
    /*@Override
    protected String getFallback() {
        return "error2";
    }*/

    // ===========================================

    // 异常处理
    /*@Override
    protected String run() throws Exception {
        int i = 1 / 0;
        return restTemplate.getForObject("http://hystrix-client-provider/hello", String.class);
    }*/

    // 异常处理
    /*@Override
    protected String getFallback() {
        return "error2: " + getExecutionException().getMessage();
    }*/

    // ===========================================

    // 请求缓存
    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://hystrix-client-provider/hello2?name={1}", String.class, name);
    }

    // 请求缓存
    @Override
    protected String getFallback() {
        return "error2: " + getExecutionException().getMessage();
    }

    // 请求缓存
    @Override
    protected String getCacheKey() {
        return name;
    }

    // ===========================================
    // 请求合并见 UserCollapser/UserCommand
}
