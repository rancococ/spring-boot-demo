package com.catvgd.springbootdemo.common.util;

import java.text.DecimalFormat;

/**
 * 将浮点数转换成中文数字大写信息
 * 
 * @author zhuyong
 * @date 2016年12月22日
 */
public class CurrencyNum2Chinese {

    /**
     * 将浮点数转换成中文数字大写信息,如: 12345.14,用中文的表达习惯是: 壹万贰仟叁佰肆拾伍元壹角肆分整 默认情况下,精确到小数点后2位(即:分), 大于2位将进行四舍五入运算.
     * 
     * @param num
     * @return
     */
    public static String translate(String num) {
        double money = 0;
        try {
            money = Double.parseDouble(num);
        } catch (Exception e) {
        }
        return translate(money, -3);
    }

    /**
     * 将浮点数转换成中文数字大写信息,如: 12345.14,用中文的表达习惯是: 壹万贰仟叁佰肆拾伍元壹角肆分整 默认情况下,精确到小数点后2位(即:分), 大于2位将进行四舍五入运算.
     * 
     * @param num
     * @return
     */
    public static String translate(double num) {
        return translate(num, -3);
    }

    /**
     * 将浮点数转换成中文数字大写信息,如: 12345.14,用中文的表达习惯是: 壹万贰仟叁佰肆拾伍元壹角肆分整 可以通过precisionPos 来指定精确到那位, precisionPos可能的值有: 值 精确到的位数 <
     * -2 0.01 -2 0.01 -1 0.1 0 个位 1 个位 2 十位 3 百位 ....... 最后的值都是一个经过四舍五入运算的值
     * 
     * @param num
     * @param precisionPos
     * @return
     */
    public static String translate(double num, int precisionPos) {
        String result = round(num, precisionPos);
        String dfResult = transDecimalFration(result);
        String intResult = transInteger(result);
        if (intResult.length() == 0) {
            intResult = "零";
        }
        return intResult + "元" + dfResult + '整';
    }

    /**
     * 将字符0-9映射成对应的大写,如果digit不在[0,9]之间,返回原来的值;
     * 
     * @param digit
     * @return
     */
    private static char digitMap(char digit) {
        char ch;
        switch (digit) {
        case '0':
            ch = '零';
            break;
        case '1':
            ch = '壹';
            break;
        case '2':
            ch = '贰';
            break;
        case '3':
            ch = '叁';
            break;
        case '4':
            ch = '肆';
            break;
        case '5':
            ch = '伍';
            break;
        case '6':
            ch = '陆';
            break;
        case '7':
            ch = '柒';
            break;
        case '8':
            ch = '捌';
            break;
        case '9':
            ch = '玖';
            break;
        default:
            ch = digit;
            break;
        }
        return ch;
    }// end digMap

    /**
     * 根据当前正在解释的数字的位置得到该位置对应的单位: 个,十,百,千 "","拾","佰","仟"
     * 
     * @param pos
     * @return
     */
    private static String unitMap(int pos) {
        String res = "";
        int relativePos = pos % 4;
        switch (relativePos) {
        case 0:
            res = "仟";
            break;
        case 1:
            res = "";
            break;
        case 2:
            res = "拾";
            break;
        case 3:
            res = "佰";
            break;
        }

        return res;
    }

    /**
     * 获得当前正在解释的位置有几亿,比如: 第1-8位 没有亿 第9-16位 有一个亿 第17-24位 有两个亿
     * 
     * @param pos
     * @return
     */
    private static String getYi(int pos) {// a hundred million
        int count = (pos - 1) / 8;
        String res = "";
        for (int i = 0; i < count; i++) {
            res += "亿";
        }

        return res;
    }

    /**
     * 获得当前正在解释的位置有几亿,只有处于第9位,第17位,第25位...才会返回 所包含的亿,其它位置都返回""
     * 
     * @param pos
     * @return
     */
    private static String getConditionalYi(int pos) {
        String res = "";
        if ((pos - 1) % 8 == 0 && pos != 1) {
            res = getYi(pos);
        }
        return res;
    }

    /**
     * 获得正在解释的位置上,是否有万,只有处于(5+ n*8) 位置上的值才会 返回"万",其它返回""
     * 
     * @param pos
     * @return
     */
    private static String getConditionalWan(int pos) {
        String res = "";
        if ((pos - 5) % 8 == 0) {
            res = "万";
        }
        return res;
    }

    /**
     * 获得正在解释的位置上,是否有万,只有处于(5+ n*8) 到(8+ n*8)位置上的值才会 返回"万",其它返回""
     * 
     * @param pos
     * @return
     */
    private static String getWan(int pos) {
        String res = "";
        if ((pos - 5) % 8 == 0 || (pos - 6) % 8 == 0 || (pos - 7) % 8 == 0 || (pos - 8) % 8 == 0) {
            res = "万";
        }
        return res;
    }

    private CurrencyNum2Chinese() {
    }

    /**
     * 根据指定的精度进行四舍五入
     * 
     * @param num
     * @param precisionPos
     * @return
     */
    private static String round(double num, int precisionPos) {
        String pattern;
        if (precisionPos <= -2) {
            pattern = "##.##";
        } else if (precisionPos == -1) {
            pattern = "##.#";
        } else if (precisionPos == 0 || precisionPos == 1) {
            pattern = "##";
        } else {
            num = num / Math.pow(10, precisionPos - 1);
            // System.out.println("Num:" + num);
            pattern = "##";
        }
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern(pattern);
        String result = df.format(num);

        for (int i = 0; i < precisionPos - 1; i++) {
            result += '0';
        }
        return result;
    }

    /**
     * 转换小数部分
     * 
     * @param num
     * @return
     */
    private static String transDecimalFration(String num) {
        String result = "";
        int indexOf = num.indexOf(".");

        if (indexOf < 0) {
            return result;
        }

        result += digitMap(num.charAt(indexOf + 1));
        if (num.charAt(indexOf + 1) != '0') {
            result += "角";
        }
        if (num.length() > indexOf + 2) {
            result += digitMap(num.charAt(indexOf + 2)) + "分";
        }
        return result;
    }

    /**
     * 转换整数部分
     * 
     * @param num
     * @return
     */
    private static String transInteger(String num) {
        String res = "";
        int indexOf = num.indexOf(".");
        if (indexOf < 0) {
            indexOf = num.length() - 1;
        } else {
            indexOf--;
        }
        int pos = 1;
        int zeroCount = 0;
        int startPos = -1;
        String lastYi = "";
        String lastWan = "";
        for (int i = indexOf; i >= 0; i--) {
            char ch = num.charAt(i);
            if (ch == ',') {
                res = ',' + res;
                continue;
            }

            if (ch == '0') {
                if (startPos == -1) {
                    startPos = pos;
                }
                lastWan = getWan(pos);
                lastYi = getYi(pos);
                pos++;
                zeroCount++;

                continue;
            } else {
                if (zeroCount > 0) {
                    if (startPos > 1) {
                        res = digitMap('0') + res;// +
                                                  // conditionalFiveUnitMap(pos)
                    }
                    if (((startPos - 1) % 4 != 0) && ((startPos - 1) / 4 == (startPos + zeroCount - 2) / 4)) {// 是否跨了阶段
                        lastWan = "";
                    }
                    if (((startPos - 1) % 8 != 0) && (startPos - 1) / 8 == (startPos + zeroCount - 2) / 8) {
                        lastYi = "";
                    }
                    if (startPos == 1 && zeroCount % 8 == 0) {
                        lastYi = "";
                        lastWan = "";
                    }

                    res = lastWan + lastYi + res;
                    lastWan = "";
                    lastYi = "";
                    zeroCount = 0;
                    startPos = -1;
                }

            }
            res = digitMap(ch) + unitMap(pos) + getConditionalWan(pos) + getConditionalYi(pos) + res;
            pos++;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(CurrencyNum2Chinese.translate("654321.99"));
        System.out.println(CurrencyNum2Chinese.translate(27910));
        System.out.println(CurrencyNum2Chinese.translate(123456789));
        System.out.println(CurrencyNum2Chinese.translate(123456789987654.5644));

    }
}