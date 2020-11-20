package jrx.anyest.table.aop;

import jrx.anyest.table.aop.annotation.InterfaceAnalysisLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author lw
 * @since 2020/10/22 16:24
 */
@Component
@Aspect
@Slf4j
public class LogAspect {


    /**
     * 配置切入点
     */
    @Pointcut("@annotation(jrx.data.hub.domain.aop.annotation.ApplicationLog)")
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
        Annotation annotation = method.getAnnotation(InterfaceAnalysisLog.class);
        String description = null;
        if (null != annotation) {
            description = ((InterfaceAnalysisLog) annotation).description();
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("[ description:{} ]: e:{} ",description , e);
        }
        return result;
    }
}
