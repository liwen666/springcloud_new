package com.temp.springboot.common.util;

import org.apache.catalina.User;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * 跳过null的value值拷贝
 *
 * @author yxy
 * @date 2018/6/20
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static void copyProperties(Object source, Object target) {

        org.springframework.beans.BeanUtils.copyProperties(source, target, getNullPropertyNames(source));

    }

    private static String[] getNullPropertyNames(Object source) {

        final BeanWrapper src = new BeanWrapperImpl(source);
        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : src.getPropertyDescriptors()) {
            if (src.getPropertyValue(pd.getName()) == null) {
                emptyNames.add(pd.getName());
            }
        }
        return new HashSet<String>().toArray(new String[emptyNames.size()]);
    }


    public static void main(String[] args) {
    }

}
