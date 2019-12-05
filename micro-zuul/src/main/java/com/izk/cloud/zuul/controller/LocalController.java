package com.izk.cloud.zuul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/11/29 18:28
 * @changeRecord
 */
@RestController
public class LocalController {
    @GetMapping("/local/test")
    public String local(){
        return "success";
    }
}
