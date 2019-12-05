package com.izk.cloud.zuul.config;

import com.izk.cloud.zuul.filter.ErrorFilter;
import com.izk.cloud.zuul.filter.IpFilter;
import com.izk.cloud.zuul.filter.LimitFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/12/2 10:50
 * @changeRecord
 */
@Configuration
public class FilterConfig {
    @Bean
    public IpFilter ipFilter(){
        return  new IpFilter();
    }
    @Bean
    public ErrorFilter errorFilter(){
        return  new ErrorFilter();
    }
    @Bean
    public LimitFilter limitFilter(){
        return  new LimitFilter();
    }
}
