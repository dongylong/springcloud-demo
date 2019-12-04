package com.izk.cloud.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    private static final String USER_URL_TEST  = "http://MICRO-USER/user/test";
    private static final String SERVICE_USER_NAME  = "MICRO-USER";
    @GetMapping("/user/test")
    public Object testUser() {
        return restTemplate.getForObject(USER_URL_TEST,String.class);
    }

    @GetMapping("/choose")
    public Object chooseUrl() {
        ServiceInstance instance = loadBalancerClient.choose(SERVICE_USER_NAME);
        return instance;
    }
}