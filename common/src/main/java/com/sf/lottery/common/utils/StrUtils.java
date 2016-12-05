package com.sf.lottery.common.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        if (src == null || src.equals("null") || src.equals("undefined")) {
            return "";
        }
        return src;
    }

    public static boolean isNull(String src) {
        if (src == null || src.trim().equals("") || src.trim().equalsIgnoreCase("undefined")) {
            return true;
        }
        return false;
    }

    public static String sha1(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for (int i = 0; i < bits.length; i++) {
            int a = bits[i];
            if (a < 0) a += 256;
            if (a < 16) buf.append("0");
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }


}
