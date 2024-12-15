package com.hmall.api.config;

import com.hmall.api.fallback.ItemClientFallbackFactory;
import com.hmall.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
//openfegin文档相关
public class DefaultFeginConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
@Bean
    public RequestInterceptor userInfoRequestInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                Long userId = UserContext.getUser();
                if (userId==null){
                    return;
                }
                template.header("user-info", userId.toString());
            }
        };
}
@Bean
   public ItemClientFallbackFactory itemClientFallbackFactory(){
    return new ItemClientFallbackFactory();
}
}
