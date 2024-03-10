package com.example.springcloudalibaba.order.service;

import com.myvocal.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * FeignClient放在interface类级别，value为微服务客户端的服务名
 * path指定调用的rest接口所在的Controller指定的RequestMapping
 *  configuration，为当前feign客户端指定配置
 */
@FeignClient(value = "stock-service", path = "/stock", configuration = FeignConfig.class)
public interface StockFeignService {

    // 声明需要调用的rest接口对应的方法, openfeign对方法的声明和springmvc相同
    @PostMapping("/reduce")
    String reduce();
}
