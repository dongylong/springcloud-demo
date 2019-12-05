package com.izk.cloud.feign;

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
public class UserHystrix implements UserFeignClient {
    @Override
    public String test() {

        return "FALSE";
    }

    @Override
    public String value(Long id) {
        return "ERROR:" +id;
    }
}
