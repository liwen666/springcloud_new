package com.anji.springbootrabbitmq.basic;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * author lw
 * date 2019/8/22  10:35
 * discribe
 */
@Component
public class RabbitQuereUtil implements ApplicationContextAware {

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    public static final Queue queue = new Queue("springboot.demo.test11", true, false, false);;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(applicationContext!=null) {
            RabbitQuereUtil.applicationContext = applicationContext;
        }
    }


    public ApplicationContext applicationContext() {
        return applicationContext;
    }

    /**
     * 在@PostConstruct注解的方法内调用,可能得到null值
     */
    @Deprecated
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name
     * @return
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
