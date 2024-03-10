package com.myvocal.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author csq
 * @description: feign拦截器配置。再在application.yml中，为具体的客户端配置拦截器
 * @date 2024/3/8 11:00
 */
@Configuration
@Slf4j
public class CustomerFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 业务逻辑
        log.info("进入了拦截器");
        String token = UUID.randomUUID().toString();
        requestTemplate.header("Authorization",token);
    }
}