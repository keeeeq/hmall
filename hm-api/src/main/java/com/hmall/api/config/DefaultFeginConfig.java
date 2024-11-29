package com.hmall.api.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
//openfegin文档相关
public class DefaultFeginConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
