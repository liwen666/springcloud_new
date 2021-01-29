package tem.inter.analysis.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import tem.inter.analysis.aop.annotation.InterfaceAnalysisRetry;
import tem.inter.analysis.utils.TablePropertiesThreadLocalHolder;

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
public class InterfaceAnalysisAspect {

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(tem.inter.analysis.aop.annotation.InterfaceAnalysisRetry)")
    public void logPointcut() {
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) {
        String key = TablePropertiesThreadLocalHolder.getProperties("sequence");
        int testaaa = TablePropertiesThreadLocalHolder.getSeq(key);
        System.out.println(key+"--->"+testaaa);
        Object[] args = joinPoint.getArgs();
        System.out.println(args);
        String kind = joinPoint.getKind();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println(signature.getParameterTypes());
        System.out.println(signature.getParameterNames());
        Method method = signature.getMethod();
        System.out.println(method.getName());
        Annotation annotation = method.getAnnotation(InterfaceAnalysisRetry.class);
        String description = null;
        if (null != annotation) {
            description = ((InterfaceAnalysisRetry) annotation).description();
        }
        Object  result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }



        return result;
    }

}
