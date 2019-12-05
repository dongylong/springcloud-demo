package com.izk.cloud.order.controller;

import com.izk.cloud.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.invoke.MethodHandles;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private UserFeignClient userFeignClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    private static final String USER_URL_TEST = "http://MICRO-USER/user/test";
    private static final String SERVICE_USER_NAME = "MICRO-USER";

    @GetMapping("/user/test")
    public Object testUser() {
        LOGGER.info("test");
        return restTemplate.getForObject(USER_URL_TEST, String.class);
    }

    @GetMapping("/user/feign/test")
    public Object testUserFeign() {
        LOGGER.info("test");
        return userFeignClient.test();
    }

    @GetMapping("/user/feign/value/{id}")
    public Object testUserFeignValue(@PathVariable("id") Long id) {
        LOGGER.info("value : {}",id);
        return userFeignClient.value(id);
    }

    @GetMapping("/choose")
    public Object chooseUrl() {
        ServiceInstance instance = loadBalancerClient.choose(SERVICE_USER_NAME);
        return instance;
    }
}