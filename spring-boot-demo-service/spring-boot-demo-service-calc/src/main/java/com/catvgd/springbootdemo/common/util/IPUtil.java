package com.catvgd.springbootdemo.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP地址处理工具
 * 
 * @author zhuyong
 * @date 2016年12月9日
 */
public class IPUtil {

    /** 主机IP */
    private static String HOST_IP;

    /**
     * 将127.0.0.1形式的IP地址转换成十进制整数
     * <p>
     * 1、通过String的indexOf方法找出IP字符串中的点"."的位置。<br>
     * 2、根据点的位置，使用String的substring方法把IP字符串分成4段。<br>
     * 3、使用Long的parseLong方法把子段转化成一个3位整数。<br>
     * 4、通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1。<br>
     * </p>
     * 
     * @param ipStr
     * @return
     */
    public static long ip2Long(String ipStr) {
        long ipLng = 0;
        try {
            if (ipStr != null && !"".equals(ipStr.trim())) {
                String[] ips = ipStr.split("\\.");
                if (ips.length == 4) {
                    for (int i = 0; i < ips.length; i++) {
                        long k = Long.parseLong(ips[i]);
                        ipLng = ipLng + k * (1L << ((3 - i) * 8));
                    }
                }
            }
        } catch (Exception e) {
        }
        return ipLng;
    }

    /**
     * 将十进制整数形式转换成127.0.0.1形式的IP地址
     * <p>
     * 1、将整数值进行右移位操作（>>>），右移24位，右移时高位补0，得到的数字即为第一段IP。<br>
     * 2、通过与操作符（&）将整数值的高8位设为0，再右移16位，得到的数字即为第二段IP。<br>
     * 3、通过与操作符吧整数值的高16位设为0，再右移8位，得到的数字即为第三段IP。<br>
     * 4、通过与操作符吧整数值的高24位设为0，得到的数字即为第四段IP。<br>
     * </p>
     * 
     * @param ipLng
     * @return
     */
    public static String long2Ip(long ipLng) {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((ipLng >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((ipLng & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((ipLng & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((ipLng & 0x000000FF)));
        return sb.toString();
    }

    /**
     * 格式化IP
     * <p>
     * 192.168.1.1=>192.168. 1. 1<br>
     * </p>
     * 
     * @param ipStr
     * @return
     */
    public static String formatIp(String ipStr) {
        return formatIp(ipStr, ' ');
    }

    /**
     * 格式化IP
     * <p>
     * 192.168.1.1=>192.168. 1. 1<br>
     * </p>
     * 
     * @param ipStr
     * @param padChar
     * @return
     */
    public static String formatIp(String ipStr, char padChar) {
        String[] ips = StringUtil.split(ipStr, '.');
        if (ips == null || ips.length != 4) {
            return ipStr;
        }
        StringBuffer sb = new StringBuffer();
        for (String temp : ips) {
            sb.append('.');
            sb.append(StringUtil.leftPad(temp, 3, padChar));
        }
        return sb.substring(1);
    }

    /**
     * 格式化IP
     * <p>
     * 192.168.1.1=>192.168. 1. 1<br>
     * </p>
     * 
     * @param ipLng
     * @return
     */
    public static String formatIp(long ipLng) {
        return formatIp(long2Ip(ipLng));
    }

    /**
     * 格式化IP
     * <p>
     * 192.168.1.1=>192.168. 1. 1<br>
     * </p>
     * 
     * @param ipLng
     * @param padChar
     * @return
     */
    public static String formatIp(long ipLng, char padChar) {
        return formatIp(long2Ip(ipLng), padChar);
    }

    /**
     * 获取主机IP
     * 
     * @return
     */
    public static String getHostIp() {
        if (HOST_IP == null) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                HOST_IP = addr.getHostAddress();
            } catch (UnknownHostException e) {
                // ignore exception
            }
        }
        return HOST_IP;
    }

}
