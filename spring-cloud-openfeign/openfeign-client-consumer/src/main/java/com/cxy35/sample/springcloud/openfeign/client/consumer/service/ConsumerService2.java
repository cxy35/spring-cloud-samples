package com.cxy35.sample.springcloud.openfeign.client.consumer.service;

import com.cxy35.sample.springcloud.common.OpenFeignService;
import org.springframework.cloud.openfeign.FeignClient;

// @FeignClient("openfeign-client-provider")
// @FeignClient(value = "openfeign-client-provider", fallback = ConsumerService2Fallback.class)
@FeignClient(value = "openfeign-client-provider", fallbackFactory = ConsumerService2FallbackFactory.class)
public interface ConsumerService2 extends OpenFeignService {
}
