package com.izk.cloud.zuul.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * @author dongyl
 * @version 1.0
 * @title
 * @description
 * @company 好未来-爱智康
 * @created 2019/10/16 18:15
 * @changeRecord
 */
public class IpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public final static String ERROR_IP = "127.0.0.1";
    private static volatile InetAddress LOCAL_ADDRESS = null;
    public final static Pattern IP_PATTERN = Pattern.
            compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})");

    public static String getRealIpAddr(HttpServletRequest request){
        String ipAddress = getUserIPString(request);
        if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
            //根据网卡取本机配置的IP
            InetAddress inet = null;
            inet = IpUtils.getLocalAddress();
            ipAddress = inet.getHostAddress();
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public static String getUserIPString(HttpServletRequest request) {
        // 优先取X-Real-IP
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if("0:0:0:0:0:0:0:1".equals(ip)) {
                ip = ERROR_IP;
            }
        }
        if ("unknown".equalsIgnoreCase(ip)) {
            ip = ERROR_IP;
            return ip;
        }
        int pos = ip.indexOf(',');
        if (pos >= 0) {
            ip = ip.substring(0, pos);
        }
        return ip;
    }

    public static InetAddress getLocalAddress() {
        if(LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        } else {
            InetAddress localAddress = getLocalAddress0();
            LOCAL_ADDRESS = localAddress;
            return localAddress;
        }
    }

    private static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if(isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable var6) {
            LOGGER.warn("Failed to retriving ip address, " + var6.getMessage(), var6);
        }

        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            if(e != null) {
                while(e.hasMoreElements()) {
                    try {
                        NetworkInterface e1 = (NetworkInterface)e.nextElement();
                        Enumeration addresses = e1.getInetAddresses();
                        if(addresses != null) {
                            while(addresses.hasMoreElements()) {
                                try {
                                    InetAddress e2 = (InetAddress)addresses.nextElement();
                                    if(isValidAddress(e2)) {
                                        return e2;
                                    }
                                } catch (Throwable var5) {
                                    LOGGER.warn("Failed to retriving ip address, " + var5.getMessage(), var5);
                                }
                            }
                        }
                    } catch (Throwable var7) {
                        LOGGER.warn("Failed to retriving ip address, " + var7.getMessage(), var7);
                    }
                }
            }
        } catch (Throwable var8) {
            LOGGER.warn("Failed to retriving ip address, " + var8.getMessage(), var8);
        }
        LOGGER.error("Could not get local host ip address, will use 127.0.0.1 instead.");
        return localAddress;
    }


    private static boolean isValidAddress(InetAddress address) {
        if(address != null && !address.isLoopbackAddress()) {
            String name = address.getHostAddress();
            return name != null && !"0.0.0.0".equals(name) && !"127.0.0.1".equals(name) && IP_PATTERN.matcher(name).matches();
        } else {
            return false;
        }
    }
}
