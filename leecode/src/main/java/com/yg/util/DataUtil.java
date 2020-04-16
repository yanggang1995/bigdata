package com.yg.util;

/**
 * 数据工具类
 *
 * @author Y.G
 * @date 2020/4/7 17:39
 **/
public class DataUtil {

    public static void main(String[] args) {
        byte tmp = (byte) Integer.parseInt("0001", 2);
        System.out.println(byteToBitStr( tmp));
    }

    public static String byteToBitStr(byte bt) {
        int tmp = bt | 256;
        String str = Integer.toBinaryString(tmp);
        return str.substring(str.length() - 8);
    }
}
