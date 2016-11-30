package com.sf.lottery.common.utils;


/**
 * 字符串处理类
 *
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/4
 */
public class StrUtils {
    public static String makeString(Object... args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object arg : args) {
            stringBuilder.append(arg);
        }
        return stringBuilder.toString();
    }

    public static String transferNull(String src) {
        if (src == null || src.equals("null") ||src.equals("undefined")) {
            return "";
        }
        return src;
    }

    public static boolean isNull(String src) {
        if (src == null || src.trim().equals("") ||src.trim().equalsIgnoreCase("undefined")) {
            return true;
        }
        return false;
    }

}
