package com.catvgd.springbootdemo.common.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 数字处理工具
 * 
 * @author zhuyong
 * @date 2016年12月22日
 */
public class NumberUtil extends NumberUtils {

    public static boolean isNull(BigDecimal data) {
        return (data == null) ? true : false;
    }

    public static boolean isZero(BigDecimal data) {
        return (data != null && new BigDecimal(0).equals(data)) ? true : false;
    }

    public static boolean isNullOrZero(BigDecimal data) {
        return (data == null || new BigDecimal(0).equals(data)) ? true : false;
    }

    public static String toString(BigDecimal data) {
        return data == null ? "" : data.toString();
    }

    public static BigDecimal nulltoZero(BigDecimal data) {
        BigDecimal rtData = new BigDecimal(0);
        if (data != null) {
            rtData = data;
        }
        return rtData;
    }

    public static boolean isOdd(int data) {
        return (data % 2 == 0) ? false : true;
    }

    public static boolean isEven(int data) {
        return (data % 2 == 0) ? true : false;
    }

    /**
     * 解析复合Long型字符串
     * 
     * @param multiLongStr
     * @param join
     * @return
     */
    public static Long[] parseMultiplexLongStr(String multiLongStr, String join) {
        String[] strArr = StringUtil.split(multiLongStr, join);
        Long[] longArr = new Long[strArr.length];
        for (int i = 0, len = strArr.length; i < len; i++) {
            longArr[i] = toLong(strArr[i], 0);
        }
        return longArr;
    }

    /**
     * 解析复合Int型字符串
     * 
     * @param multiLongStr
     * @param join
     * @return
     */
    public static Integer[] parseMultiplexIntStr(String multiLongStr, String join) {
        String[] strArr = StringUtil.split(multiLongStr, join);
        Integer[] longArr = new Integer[strArr.length];
        for (int i = 0, len = strArr.length; i < len; i++) {
            longArr[i] = toInt(strArr[i], 0);
        }
        return longArr;
    }

}
