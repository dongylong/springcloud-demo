package com.izk.cloud.feign;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/12/4 18:26
 * @changeRecord
 */
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        //4种级别
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options(){
        return new Request.Options(5000,10000);
    }
}
