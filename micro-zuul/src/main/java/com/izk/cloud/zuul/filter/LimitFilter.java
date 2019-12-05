package com.izk.cloud.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/12/2 17:38
 * @changeRecord
 */
public class LimitFilter extends ZuulFilter {

    public static volatile RateLimiter RATE_LIMITER = RateLimiter.create(1000);

    public LimitFilter() {
        super();
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RATE_LIMITER.acquire();
        return null;
    }
}
