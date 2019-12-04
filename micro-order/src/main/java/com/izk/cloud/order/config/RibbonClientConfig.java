package com.izk.cloud.order.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/12/4 16:31
 * @changeRecord
 */
@RibbonClient(name = "micro-user",configuration = BeanConfig.class)
public class RibbonClientConfig {

}
