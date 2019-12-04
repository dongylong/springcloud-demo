package com.izk.cloud.eureka.listener;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/11/28 14:38
 * @changeRecord
 */
@Component
public class EurekaStateChangeListener {

    /**
     * EurekaInstanceRenewedEvent 服务续约事件
     * @param event
     */
    @EventListener
    public void listener(EurekaInstanceRenewedEvent event){
        System.err.println(event.getServerId()+"\t" +event.getAppName() +" 服务进行续约");
    }

    /**
     * EurekaInstanceRegisteredEvent 服务注册事件
     * @param event
     */
    @EventListener
    public void listener(EurekaInstanceRegisteredEvent event){
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.err.println(instanceInfo.getAppName() +" 进行注册");
    }

    /**
     * EurekaRegistryAvailableEvent 注册中心启动事件
     * @param event
     */
    @EventListener
    public void listener(EurekaRegistryAvailableEvent event){
        System.err.println("注册中心启动");
    }

    /**
     * EurekaServerStartedEvent 启动事件
     * @param event
     */
    @EventListener
    public void listener(EurekaServerStartedEvent event){
        System.err.println("启动");
    }

    /**
     * EurekaServerStartedEvent 服务下线事件
     * @param event
     */
    @EventListener
    public void listener(EurekaInstanceCanceledEvent event){
        System.err.println(event.getServerId()+"\t" +event.getAppName() +" 服务下线");
    }
}
