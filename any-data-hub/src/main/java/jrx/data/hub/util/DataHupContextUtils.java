package jrx.data.hub.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2019/11/12 14:22
 */
@Component
public class DataHupContextUtils implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;


    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        DataHupContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象 这里重写了bean方法，起主要作用
     */
    public static <T> T getBean(Class<T> t) {
        return applicationContext.getBean(t);
    }

    /**
     * 获取对象 这里重写了bean方法，起主要作用
     */
    public static <T> T getBean(String beanId, Class<T> t) {
        return applicationContext.getBean(beanId, t);
    }



}