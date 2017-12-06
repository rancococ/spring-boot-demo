package com.catvgd.springbootdemo.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取主机信息工具类
 * 
 * @author ranyong
 * @date 2017年3月8日
 */
public class HostUtil {

    /** 主机域名 */
    private static String HOST_DOMAIN;
    private static String HOST_DOMAIN_UNKNOWN = "UnknownHostDomainName";
    /** 主机名称 */
    private static String HOST_NAME;
    private static String HOST_NAME_UNKNOWN = "UnknownHostName";
    /** 主机地址 */
    private static String HOST_ADDR;
    private static String HOST_ADDR_UNKNOWN = "UnknownHostAddr";

    /**
     * 获取主机域名
     * 
     * @return
     */
    public static String getHostDomainName() {
        if (HOST_DOMAIN == null) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                HOST_DOMAIN = addr.getCanonicalHostName();
            } catch (UnknownHostException e) {
                // ignore exception
                HOST_DOMAIN = HOST_DOMAIN_UNKNOWN;
            }
        }
        return HOST_DOMAIN;
    }

    /**
     * 获取主机名称
     * 
     * @return
     */
    public static String getHostName() {
        if (HOST_NAME == null) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                HOST_NAME = addr.getHostName();
            } catch (UnknownHostException e) {
                // ignore exception
                HOST_NAME = HOST_NAME_UNKNOWN;
            }
        }
        return HOST_NAME;
    }

    /**
     * 获取主机地址
     * 
     * @return
     */
    public static String getHostAddr() {
        if (HOST_ADDR == null) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                HOST_ADDR = addr.getHostAddress();
            } catch (UnknownHostException e) {
                // ignore exception
                HOST_ADDR = HOST_ADDR_UNKNOWN;
            }
        }
        return HOST_ADDR;
    }

}
