package com.izk.cloud.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/12/5 11:13
 * @changeRecord
 */
@Component
public class UserHystrixFactory implements FallbackFactory<String> {

    @Override
    public String create(Throwable throwable) {
        return "Error:"+throwable.getMessage();
    }
}
