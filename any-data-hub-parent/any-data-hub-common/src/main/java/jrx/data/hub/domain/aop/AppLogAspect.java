package jrx.data.hub.domain.aop;

import jrx.data.hub.domain.aop.annotation.ApplicationLog;
import jrx.data.hub.domain.exception.DataException;
import jrx.data.hub.util.DataResponse;
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
public class AppLogAspect {

    private long currentTime = 0L;

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
        Annotation annotation = method.getAnnotation(ApplicationLog.class);
        String description = null;
        if (null != annotation) {
            description = ((ApplicationLog) annotation).description();
        }
        Object result = null;
        currentTime = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("[" + description + "]:  " + e.getMessage(), e);
//            AlertUtil.sendAlertMsg("["+description  +"]:  ",  e.getMessage(), AlertLevelEnum.ERROR);
            DataResponse dataResponse = DataResponse.error();
            if (e instanceof DataException) {
                dataResponse.setReason(description + "异常," + e.getMessage());
            } else {
                dataResponse.setReason(description + "异常");
            }
            return dataResponse;
        }
        return result;
    }
}
