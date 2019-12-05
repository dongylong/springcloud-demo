package com.izk.cloud.zuul.entity;

import org.springframework.stereotype.Component;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/11/29 18:45
 * @changeRecord
 */
@Component
public class BasicConf {
    private String ipStr = "127.0.0.1";

    public String getIpStr() {
        return ipStr;
    }

    public void setIpStr(String ipStr) {
        this.ipStr = ipStr;
    }
}
