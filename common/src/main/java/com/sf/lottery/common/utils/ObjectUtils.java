package com.sf.lottery.common.utils;


import com.sf.lottery.common.domain.ClassIntrospector;
import com.sf.lottery.common.domain.ObjectInfo;

/**
 * 利用ClassIntrospector计算对象大小
 *
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/4
 */
public class ObjectUtils {
    private final static ClassIntrospector ci = new ClassIntrospector();

    public static long getObjectSize(Object object){
        ObjectInfo res = null;
        try {
            res = ci.introspect(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res.getDeepSize();
    }
}
