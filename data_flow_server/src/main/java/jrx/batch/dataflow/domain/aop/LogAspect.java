package jrx.batch.dataflow.domain.aop;

import jrx.batch.dataflow.domain.aop.annotation.BatchNodeLog;
import jrx.batch.dataflow.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Description:    java类作用描述
 * author:     lw
 * date:     2019/5/26 12:22
 * Version:        1.0
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    private long currentTime = 0L;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(jrx.batch.dataflow.domain.aop.annotation.BatchNodeLog)")
    public void logPointcut() {
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Annotation annotation = method.getAnnotation(BatchNodeLog.class);
        String description = null;
        if (null!=annotation) {
            description = ((BatchNodeLog) annotation).description();
        }
        Object result = null;
        currentTime = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            log.error("["+description  +"]:  "+ e.getMessage(), e);
//            AlertUtil.sendAlertMsg("["+description  +"]:  ",  e.getMessage(), AlertLevelEnum.ERROR);
            return JsonResult.error(description+" 异常");
        }
        return result;
    }
}
