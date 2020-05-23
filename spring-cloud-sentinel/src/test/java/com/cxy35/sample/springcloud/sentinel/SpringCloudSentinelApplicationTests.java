package com.cxy35.sample.springcloud.sentinel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootTest
class SpringCloudSentinelApplicationTests {

    @Test
    void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 15; i++) {
            String s = restTemplate.getForObject("http://127.0.0.1:8081/hello", String.class);
            System.out.println(s + ":" + new Date());
        }
    }

}
