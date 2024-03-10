package com.example.springcloudalibaba.order.controller;

import com.example.springcloudalibaba.order.service.StockFeignService;
import com.myvocal.util.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author csq
 * @description:
 * @date 2024/3/5 15:47
 */
@RestController
@RequestMapping("/order")
@Slf4j
@RefreshScope
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

//    @Lazy
    @Autowired
    private StockFeignService stockFeignService;

    @Value("${user.name}")
    private String username;

    /**
     * 直接通过http请求调用*
     */
    @PostMapping("/add")
    public String add(HttpServletRequest request){
        log.info("ip:{}, miwen:{}", request.getPathInfo(), AesUtil.encrypt("1234", request.getPathInfo()));
        // restTemplate需要负载均衡注解，此时调用才会走nacos服务发现
        // 调用url的域名应改为目标服务的name（不同命名空间、group的服务会发现失败，跨组需要额外实现）
        String result = restTemplate.exchange("http://stock-service/stock/reduce", HttpMethod.POST, null, String.class)
                .getBody();
        return "下单成功" + result;
    }

    /**
     * 通过feign调用*
     * @return
     */
    @PostMapping("/add-feign")
    public String addFeign(){
        log.info("user.name:{}", username);
        return "下单成功-feign, " + stockFeignService.reduce();
    }
}
