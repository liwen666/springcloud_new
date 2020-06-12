package com.example.component;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2015/12/16.
 */
@Aspect     //注解的方式配置aop
@Configuration
public class dataSourceAspect {
    @Pointcut("execution(* com..Demo*.*(..))")
    private void anyMethod(){}//定义一个切入点
 
    @Before("anyMethod()")
    public void dataSourceChange()
    {

        System.out.println("******************************************************");

        System.out.print("更改数据源为cc");
//        HandlerDataSource.setDataSource("2");
        /*这里根据用户的类型来更改对应的数据源*/
    }
}