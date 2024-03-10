package com.myvocal.config;

import feign.Feign;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author csq
 * @description:
 * @date 2024/3/8 10:13
 */
// 开启Configuration注解，则全局生效，关闭则在注解中指定生效
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel()
    {
        return Logger.Level.FULL;
    }

    // 如果你需要对特定的Feign客户端定制日志级别
    @Bean
    public Feign.Builder feignBuilder(Logger.Level feignLoggerLevel) {
        return Feign.builder()
                .logger(new Logger.ErrorLogger())
                .logLevel(feignLoggerLevel);
    }
}
