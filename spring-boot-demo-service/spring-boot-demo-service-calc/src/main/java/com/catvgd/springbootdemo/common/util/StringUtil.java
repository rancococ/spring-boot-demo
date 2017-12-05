package com.catvgd.springbootdemo.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串处理工具
 * 
 * @author zhuyong
 * @date 2016年12月22日
 */
public class StringUtil extends StringUtils {

    public StringUtil() {
        super();
    }

    public static boolean isNull(String data) {
        return (data == null || data.trim().length() < 1);
    }

    public static boolean isNotNull(String data) {
        return !isNull(data);
    }

    public static String toNull(String data) {
        return isNull(data) ? null : data;
    }

    public static String toString(String data) {
        return isNull(data) ? "" : data;
    }

    /**
     * 生成随机字符串
     * 
     * @param size
     * @return
     */
    public static String getRandomString(int size) {
        char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h',
                'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(c[Math.abs(random.nextInt()) % c.length]);
        }
        return sb.toString();
    }

    /**
     * 格式化数字
     * 
     * @param str
     * @return
     */
    public static String toNumberFormat(String str) {
        NumberFormat n = NumberFormat.getNumberInstance();
        n.setGroupingUsed(true);
        double d;
        String outStr = null;
        try {
            d = Double.parseDouble(str);
            outStr = n.format(d);
        } catch (Exception e) {
        }
        return outStr;
    }

    /**
     * 格式化数字
     * 
     * @param str
     * @return
     */
    public static String toDecimalFormat(String str) {
        return toDecimalFormat(str, "##,###,###,###,##0.00000");
    }

    /**
     * 格式化数字
     * 
     * @param str
     * @param pattern
     * @return
     */
    public static String toDecimalFormat(String str, String pattern) {
        if (isEmpty(pattern)) {
            return str;
        }
        DecimalFormat fmt = new DecimalFormat(pattern);
        fmt.setGroupingUsed(true);
        String outStr = null;
        double d;
        try {
            d = Double.parseDouble(str);
            outStr = fmt.format(d);
        } catch (Exception e) {
        }
        return outStr;
    }

    /**
     * 格式化金额
     * 
     * @param str
     * @return
     */
    public static String toCurrencyFormat(String str) {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.CHINA);
        n.setGroupingUsed(true);
        double d;
        String outStr = null;
        try {
            d = Double.parseDouble(str);
            outStr = n.format(d);
        } catch (Exception e) {
        }
        return outStr;
    }

    /**
     * 格式化百分比
     * 
     * @param str
     * @return
     */
    public static String toPercentFormat(String str) {
        NumberFormat n = NumberFormat.getPercentInstance();
        n.setGroupingUsed(true);
        n.setMinimumFractionDigits(2);
        n.setMinimumIntegerDigits(1);
        double d;
        String outStr = null;
        try {
            d = Double.parseDouble(str);
            outStr = n.format(d);
        } catch (Exception e) {
        }
        return outStr;
    }

    public static String unCamelUpperCase(String source) {
        String[] parts = split(source, "_");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            sb.append(capitalize(part));
        }
        source = capitalize(sb.toString());
        Pattern p = Pattern.compile("([A-Z]?[a-z0-9]*)");
        Matcher m = p.matcher(source);
        sb = new StringBuilder();
        while (m.find()) {
            sb.append(upperCase(m.group())).append("_");
        }
        return substringBefore(sb.toString(), "__");
    }

    public static String unCamelLowerCase(String source) {
        String[] parts = split(source, "_");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            sb.append(capitalize(part));
        }
        source = capitalize(sb.toString());
        Pattern p = Pattern.compile("([A-Z]?[a-z0-9]*)");
        Matcher m = p.matcher(source);
        sb = new StringBuilder();
        while (m.find()) {
            sb.append(lowerCase(m.group())).append("_");
        }
        return substringBefore(sb.toString(), "__");
    }

    public static String camelUpperCase(String source) {
        String[] parts = split(source, "_");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            sb.append(capitalize(lowerCase(part)));
        }
        return sb.toString();
    }

    public static String camelLowerCase(String source) {
        String[] parts = split(source, "_");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            sb.append(capitalize(lowerCase(part)));
        }
        return uncapitalize(sb.toString());
    }

    public static String camel4underline(String param) {
        Pattern p = Pattern.compile("[A-Z]");
        if (param == null || param.equals("")) {
            return "";
        }
        StringBuilder builder = new StringBuilder(param);
        Matcher mc = p.matcher(param);
        int i = 0;
        while (mc.find()) {
            builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            i++;
        }
        if ('_' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    public static String getCamelCaseString(String inputString, boolean firstCharacterUppercase) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            switch (c) {
            case '_':
            case '-':
            case '@':
            case '$':
            case '#':
            case ' ':
            case '/':
            case '&':
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
                break;
            default:
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
                break;
            }
        }
        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }
        return sb.toString();
    }

    public static String fixPath(String path) {
        String fixedPath = (path == null ? "" : path.trim());
        if (isNotEmpty(fixedPath)) {
            fixedPath = fixedPath.replaceAll("\\\\+", "/");
            fixedPath = fixedPath.replaceAll("/+", "/");
        }
        return fixedPath;
    }

    public static String fixPathStartWithSlash(String path) {
        String fixedPath = (path == null ? "" : path.trim());
        if (isNotEmpty(fixedPath)) {
            fixedPath = fixedPath.replaceAll("\\\\+", "/");
            fixedPath = fixedPath.replaceAll("/+", "/");
            fixedPath = startsWith(fixedPath, "/") ? fixedPath : ("/" + fixedPath);
        }
        return fixedPath;
    }

    public static String fixPathRemoveFirstSlash(String path) {
        String fixedPath = (path == null ? "" : path.trim());
        if (isNotEmpty(fixedPath)) {
            fixedPath = fixedPath.replaceAll("\\\\+", "/");
            fixedPath = fixedPath.replaceAll("/+", "/");
            fixedPath = startsWith(fixedPath, "/") ? substringAfter(fixedPath, "/") : fixedPath;
        }
        return fixedPath;
    }

    public static String fixPathEndWithSlash(String path) {
        String fixedPath = (path == null ? "" : path.trim());
        if (isNotEmpty(fixedPath)) {
            fixedPath = fixedPath.replaceAll("\\\\+", "/");
            fixedPath = fixedPath.replaceAll("/+", "/");
            fixedPath = endsWith(fixedPath, "/") ? fixedPath : (fixedPath + "/");
        }
        return fixedPath;
    }

    public static String fixPathRemoveLastSlash(String path) {
        String fixedPath = (path == null ? "" : path.trim());
        if (isNotEmpty(fixedPath)) {
            fixedPath = fixedPath.replaceAll("\\\\+", "/");
            fixedPath = fixedPath.replaceAll("/+", "/");
            fixedPath = endsWith(fixedPath, "/") ? substringBeforeLast(fixedPath, "/") : fixedPath;
        }
        return fixedPath;
    }

    public static String fixFolder(String folder) {
        String tempFolder = folder;
        if (isNotEmpty(tempFolder)) {
            // 只允许字母和数字._
            String regEx = "[^a-zA-Z0-9._]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(tempFolder);
            tempFolder = trimToEmpty(m.replaceAll(""));
        }
        return tempFolder;
    }

    /**
     * @Title fixStrForJson
     * @Description 转化为json的时候某些字符转化不了
     * @return
     */
    public static String fixStrForJson(String str) {
        if (StringUtil.isNotNull(str)) {
            str = str.replaceAll("\'", "’");
            str = str.replaceAll("\"", "”");
        }
        return str;
    }

    /**
     * 测试
     * 
     * @param args
     */
    public static void main(String args[]) {
        // System.out.println(StringUtil.getRandomString(32));
        // System.out.println(StringUtil.insertUnderLineBeforeUpperCase("U"));
        // System.out.println(StringUtil.insertUnderLineBeforeUpperCase("UU"));
        // System.out.println(StringUtil.insertUnderLineBeforeUpperCase("userId"));
        // System.out.println(StringUtil.insertUnderLineBeforeUpperCase("userID"));
        // System.out.println(StringUtil.insertUnderLineBeforeUpperCase("UserIdNameAA"));
        // System.out.println(StringUtil.insertUnderLineBeforeUpperCase("UserIdNameAAa"));
        //
        // String str = "0600450625465.5626";
        // System.out.println(StringUtil.toNumberFormat(str));
        // System.out.println(StringUtil.toDecimalFormat(str));
        // System.out.println(StringUtil.toCurrencyFormat(str));
        // System.out.println(StringUtil.toPercentFormat("0.8568"));
        // System.out.println(StringUtil.toPercentFormat("0.00308"));

        System.out.println(StringUtil.unCamelUpperCase("A"));
        System.out.println(StringUtil.unCamelUpperCase("Aa"));
        System.out.println(StringUtil.unCamelUpperCase("AA"));
        System.out.println(StringUtil.unCamelUpperCase("AAa2"));
        System.out.println(StringUtil.unCamelUpperCase("Aa1aB1"));
        System.out.println(StringUtil.unCamelUpperCase("AaaB2a_"));
        System.out.println(StringUtil.unCamelUpperCase("Abc1Def"));
        System.out.println(StringUtil.unCamelUpperCase("abcDef"));
        System.out.println(StringUtil.unCamelLowerCase("ABcDef"));
        System.out.println(StringUtil.unCamelLowerCase("ABcDef_"));
        System.out.println(StringUtil.unCamelLowerCase("ABc_Def_"));
        System.out.println(StringUtil.unCamelLowerCase("A_Bc_de_f1_"));
        System.out.println(StringUtil.camelUpperCase("ABCC_DEF"));
        System.out.println(StringUtil.camelLowerCase("ABCC_DEF"));
        // System.out.println(StringUtil.camelUpperCase("ABcc_DEF_"));
        // System.out.println(StringUtil.camelUpperCase("_ABcc_DEFe_"));
        // System.out.println(StringUtil.camelUpperCase("_ABc_c_DEF_"));
        // System.out.println(StringUtil.camelUpperCase("_ABc_1_c_DEF_"));
        System.out.println("===");
        System.out.println(StringUtil.camel4underline("_ef_"));
        System.out.println(StringUtil.camel4underline("a_ef"));
        System.out.println(StringUtil.camel4underline("A_ef"));
        System.out.println(StringUtil.camel4underline("Aef"));
        System.out.println(StringUtil.camel4underline("AbcDDD"));
        System.out.println(StringUtil.camel4underline("abc_ef"));
        System.out.println(StringUtil.camel4underline("abcDef"));
        System.out.println(StringUtil.camel4underline("abcDef"));
        System.out.println(StringUtil.camel4underline("abcDef"));
        System.out.println(StringUtil.camel4underline("abcDef1"));
        System.out.println(StringUtil.camel4underline("AbcDef"));
    }
}
