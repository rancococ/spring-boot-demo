package com.catvgd.springbootdemo.common.util;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Color工具类
 * 
 * @author zhuyong
 * @date 2017年2月4日
 */
public class ColorUtil {

    private static Random random = new SecureRandom();

    /**
     * 随机获取十六进制的颜色代码.例如 "#6E36B4" , For HTML ,
     * 
     * @return String
     */
    public static String getRandColorCode() {
        // 产生 RGB的16进制值
        String r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        String g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        String b = Integer.toHexString(random.nextInt(256)).toUpperCase();
        // 小于10的前面补0
        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;
        return r + g + b;
    }

    /**
     * 随机获取十六进制的颜色代码.例如 "#6E36B4" , For HTML
     * 
     * @param count 个数
     * @return String
     */
    public static String[] getRandColorCode(int count) {
        String[] codes = new String[count];
        for (int i = 0; i < count; i++) {
            codes[i] = getRandColorCode();
        }
        return codes;
    }

    /**
     * 取得颜色的16进制值
     * 
     * @param color
     * @return hexCode
     */
    public static String getColorHexCode(Color color) {
        String r = Integer.toHexString(color.getRed()).toUpperCase();
        String g = Integer.toHexString(color.getGreen()).toUpperCase();
        String b = Integer.toHexString(color.getBlue()).toUpperCase();
        // 小于10的前面补0
        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;
        return r + g + b;
    }

}
