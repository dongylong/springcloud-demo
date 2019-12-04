package com.izk.cloud.order.config;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/12/4 16:13
 * @changeRecord
 */
public class MyRule implements IRule {
    private ILoadBalancer lb;
    @Override
    public Server choose(Object key) {
        List<Server> servers  = lb.getAllServers();
        for(Server server :lb.getAllServers()){
            System.out.println("serverhostport:"+server.getHostPort());
        }
        return servers.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb=lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }
}
