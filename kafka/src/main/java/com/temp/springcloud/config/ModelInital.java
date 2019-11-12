package com.temp.springcloud.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
 
/**
 * @ClassName ModelInital
 * @Description 初始化注释Model
 * @Date 2019/3/28 0028 15:14
 * @Version 0.1
 **/
@Component
public class ModelInital {
 
    @Autowired
    private ConfigurableListableBeanFactory beanFactory;
 
    @Autowired
    private ApplicationContext applicationContext;

 
 
    private static final String RESOURCE_PATTERN = "com.xxx";
 
    /**
     * 初始化方法
     */
    @PostConstruct
    public void init() {
        // 使用自定义扫描类，针对@Model进行扫描
        AnnotationScanner scanner = AnnotationScanner.getScanner((BeanDefinitionRegistry) beanFactory, Model.class);
        scanner.doScan(RESOURCE_PATTERN).forEach(beanDefinitionHolder -> {
                    Object o = applicationContext.getBean(beanDefinitionHolder.getBeanName());
                    Class<?> clazz = o.getClass();
                    Model model = clazz.getAnnotation(Model.class);
                    String  newName = model.tableName();
                    Map<Object, Object> map = new HashMap<>();
                    if (!StringUtils.isEmpty(newName)) {
//自己业务逻辑产生的结果Map(我这里是获取表的注释)
//                        map = xxxservice.findComments(newName);
                    }
                    for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                        try {
                            Field[] fields = clazz.getDeclaredFields();
                            for (Field field : fields) {
                                field.setAccessible(true);
                                ModelProperty modelProperty = field.getAnnotation(ModelProperty.class);
                                if (modelProperty != null && !StringUtils.isEmpty(modelProperty.value())) {
                                    map.put(field.getName(), modelProperty.value());
                                }
                            }
 
                        } catch (Exception e) {
                            //doNothing
                        }
                    }
                    //重新注入
            if (StringUtils.isEmpty(newName)) {
                newName = "new_Name";
            }
            ((BeanDefinitionRegistry) beanFactory).removeBeanDefinition(beanDefinitionHolder.getBeanName());
            GenericBeanDefinition beanDef = new GenericBeanDefinition();
            beanDef.setBeanClass(Map.class);
            beanDef.setPropertyValues(new MutablePropertyValues(map));
            ((BeanDefinitionRegistry) beanFactory).registerBeanDefinition(newName, beanDef);
        });
    }
}