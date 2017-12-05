package com.catvgd.springbootdemo.common.util;

/**
 * 二进制工具类
 * 
 * @author zhuyong
 * @date 2017年2月4日
 */
public class DigitUtil {

    private final static String[] arr_16 = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100",
            "1101", "1110", "1111" };
    private final static String[] arr_32 = { "00000", "00001", "00010", "00011", "00100", "00101", "00110", "00111", "01000", "01001", "01010",
            "01011", "01100", "01101", "01110", "01111", "10000", "10001", "10010", "10011", "10100", "10101", "10110", "10111", "11000", "11001",
            "11010", "11011", "11100", "11101", "11110", "11111" };
    private final static String[] arr_64 = { "000000", "000001", "000010", "000011", "000100", "000101", "000110", "000111", "001000", "001001",
            "001010", "001011", "001100", "001101", "001110", "001111", "010000", "010001", "010010", "010011", "010100", "010101", "010110",
            "010111", "011000", "011001", "011010", "011011", "011100", "011101", "011110", "011111", "100000", "100001", "100010", "100011",
            "100100", "100101", "100110", "100111", "101000", "101001", "101010", "101011", "101100", "101101", "101110", "101111", "110000",
            "110001", "110010", "110011", "110100", "110101", "110110", "110111", "111000", "111001", "111010", "111011", "111100", "111101",
            "111110", "111111" };
    private final static char[] char_arr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@' };

    public static String hexTo32(String str, int n1, int n2) {
        String result_str = "";
        String s = "", a = "";
        String[] arr = new String[0];
        int b = 0;
        String[] arrt = new String[0];
        switch (n1) {
        case 16:
            arr = arr_16;
            break;
        case 32:
            arr = arr_32;
            break;
        case 64:
            arr = arr_64;
            break;
        }
        switch (n2) {
        case 16:
            arrt = arr_16;
            b = 4;
            break;
        case 32:
            arrt = arr_32;
            b = 5;
            break;
        case 64:
            arrt = arr_64;
            b = 6;
            break;
        }
        for (int i = 0; i < str.length(); i++) {
            s += arr[getCharArrIndex(char_arr, str.charAt(i))];
        }
        for (int i = 0; i < b - s.length() % b; i++) {
            a += '0';
        }
        s = a + s;
        for (int i = 0; i < s.length(); i += b) {
            result_str += char_arr[getStringArrIndex(arrt, s.substring(i, i + b))];
        }
        for (int i = 0; i < result_str.length(); i++) {
            if (result_str.charAt(i) != '0') {
                return result_str.substring(i, result_str.length());
            }
        }
        return result_str;
    }

    private static int getCharArrIndex(char[] cs, char c) {
        int index = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == c) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static int getStringArrIndex(String[] strs, String s) {
        int index = 0;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(s)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
