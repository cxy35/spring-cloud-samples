package com.cxy35.sample.seata.storage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.cxy35.sample.seata.storage.dao"})
public class MyBatisConfig {
}
