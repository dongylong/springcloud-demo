package com.izk.cloud.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.lang.invoke.MethodHandles;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/12/3 16:07
 * @changeRecord
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static void main(String[] args) {
        SpringApplication.run(MethodHandles.lookup().lookupClass(), args);
    }
}
