package com.izk.cloud.user.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/12/3 16:35
 * @changeRecord
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Resource
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/test")
    public String test() {
        LOGGER.info("test");
        return "SUCCESS。port:" + serverPort;
    }

    @GetMapping("/value/{id}")
    public String value(@PathVariable Long id) {
        LOGGER.info("value : {}",id);

        return "SUCCESS。port:" + serverPort + " id:" + id;
    }

    @GetMapping("/eureka/infos")
    public List<InstanceInfo> eurekaInfos() {
        List<InstanceInfo> instanceInfos = eurekaClient.getInstancesByVipAddress(appName, false);
        return instanceInfos;
    }

    @GetMapping("/discovery/infos")
    public List<ServiceInstance> discoveryClientInfos() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(appName);
        return serviceInstances;
    }
}
