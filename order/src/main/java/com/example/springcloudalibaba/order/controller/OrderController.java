package com.example.springcloudalibaba.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author csq
 * @description:
 * @date 2024/3/5 15:47
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/add")
    public String add(){
        String result = restTemplate.exchange("http://localhost:8082/stock/reduce", HttpMethod.POST, null, String.class)
                .getBody();
        return "下单成功" + result;
    }
}
