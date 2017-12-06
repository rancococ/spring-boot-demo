package com.catvgd.springbootdemo.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 字符串MD5工具
 * 
 * @author zhuyong
 * @date 2016年12月22日
 */
public class Md5Util {
    /**
     * String MD5 加密
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }

    /**
     * 对象 MD5 加密
     */
    public static String getMD5Str(Object... objects) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : objects) {
            stringBuilder.append(object.toString());
        }
        return getMD5Str(stringBuilder.toString());
    }

    /**
     * 测试
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println(Md5Util.getMD5Str("这里是测试"));
    }
}
