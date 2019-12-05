package com.izk.cloud.zuul.filter;

import com.izk.cloud.zuul.entity.BasicConf;
import com.izk.cloud.zuul.utils.IpUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/11/29 18:42
 * @changeRecord
 */
public class IpFilter extends ZuulFilter {
    @Resource
    private BasicConf basicConf;

    public IpFilter() {
        super();
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Object success = ctx.get("isSuccess");

        return success == null? true:Boolean.parseBoolean(success.toString());
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String ip = IpUtils.getRealIpAddr(ctx.getRequest());
        if(StringUtils.isNotBlank(ip)
        && basicConf!=null
        && basicConf.getIpStr().contains(ip)){
            ctx.setSendZuulResponse(false);
            ctx.set("isSuccess",false);
            System.out.println("filter");
            return null;
        }
        return null;
    }
}
