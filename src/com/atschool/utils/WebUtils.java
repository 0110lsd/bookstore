package com.atschool.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {

    /**
     * 根据前端传递信息封装为Bean对象
     * @param value
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBean(Map value, T bean) {

        try {
            BeanUtils.populate(bean, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return bean;
    }

    public static int parseInt(String val, int defaultVal) {
        int res = defaultVal;
        try {
            res = Integer.parseInt(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
