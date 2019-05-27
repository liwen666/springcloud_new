package com.lw.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class SpringBeanUtils {
    public static <T> T findBean(ConfigurableBeanFactory factory, Class<T>clazz, String beanName){
        String[] beanNamesForType = ((ConfigurableListableBeanFactory) factory).getBeanNamesForType(clazz);
        try {
            T bean = (T) factory.getBean(beanName);
        } catch (BeansException e) {
            System.out.println("******************************************************");
            /**
            *
            *
            **/
//           throw new Exception();
        }
        return null;
    }
}
