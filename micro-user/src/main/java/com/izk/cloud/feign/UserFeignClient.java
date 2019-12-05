package com.izk.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/12/4 18:05
 * @changeRecord
 */
@FeignClient(value = "micro-user"
        , path = "/user"
//        ,fallback = UserHystrix.class
        ,fallbackFactory = UserHystrixFactory.class
)
public interface UserFeignClient {
    @GetMapping("/test")
    String test();

    @GetMapping("/value/{id}")
    String value(@PathVariable("id") Long id);
}
