package com.example.springcloudalibaba.stock.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author csq
 * @description: 库存
 * @date 2024/3/5 15:47
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @PostMapping("/reduce")
    public String add(){
        return "扣减库存成功";

    }
}
