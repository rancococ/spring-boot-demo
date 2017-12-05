package com.catvgd.springbootdemo.common.util;


/**
 * 系统共通常量
 * 
 * @author zhuyong
 * @date 2016年11月24日
 */
public interface ConstantCommon extends ConstantCache {

    /** 系统application */
    public static final String APPLICATION_CONTEXT = "applicationContext";
    /** 用系统application启动版本 */
    public static final String APPLICATION_VERSION = DateUtil.getDateSecondFormat();
    /** 主机名称 */
    public static final String HOST_NAME = HostUtil.getHostName();
    /** 主机地址 */
    public static final String HOST_ADDR = HostUtil.getHostAddr();
    /** 主机IP */
    public static final String HOST_IP = IPUtil.getHostIp();
    /** 开发者账号 */
    public static final String DEV = "dev";
    /** 管理员账号 */
    public static final String ADMIN = "admin";
}
