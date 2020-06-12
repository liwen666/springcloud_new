package com.example.component;

import com.example.entity.DataSourceConfigDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

/**
 * @author sukang
 */

@Aspect
@ManagedBean
public class HandlerDataSourceAop implements Ordered {

    @Inject
    private DataSourceConfigDao sourceConfigDao;

    /**
     *   //@within 在类上设置
        // @annotation 在方法上进行设置
     */
    @Pointcut("@annotation(com.example.component.DynamicDataSource) || @within(com.example.component.DynamicDataSource)")
    private void pointcut(){}

    @Around("pointcut()")
    public Object beforeDataSource(ProceedingJoinPoint joinPoint) throws Throwable{

        Object[] args = joinPoint.getArgs();

        String code = "";
        //设置一个参数测试
        for (Object arg : args) {
            code = String.valueOf(arg);
        }
        System.out.println("本方法配指定的code为::" + code);

//        if (!sourceConfigDao.isExit(code)){
//            throw new IllegalArgumentException("参数异常未找到code：" + code );
//        }

        HandlerDataSource.setDataSource(code);

        try {
            System.out.println("当前配置的数据源为" + HandlerDataSource.getDataSource());
            return joinPoint.proceed();
        } finally {
            HandlerDataSource.clearDataSource();
        }
    }



    @Override
    public int getOrder() {
        return 1;
    }
}
