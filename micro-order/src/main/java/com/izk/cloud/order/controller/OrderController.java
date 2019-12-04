package com.izk.cloud.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/12/3 17:18
 * @changeRecord
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    private static final String USER_URL_TEST  = "http://localhost:7900/user/test";
    @GetMapping("/user/test")
    public Object testUser() {
        return restTemplate.getForObject(USER_URL_TEST,String.class);
    }
}