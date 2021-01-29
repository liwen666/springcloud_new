//package tem.inter.analysis.aop;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class LoggingAspect {
//
//    /**定义一个方法，用于声明切面表达式，该方法中什么也不需要。使用是只需要引用该方法名即可*/
////    @Pointcut("execution(* *.*(..))")
////    @Pointcut("execution(* tem.inter.analysis.rest.DemoTestRest+.*(..))")
//    @Pointcut("execution(@tem.inter.analysis.aop.annotation.InterfaceAnalysisRetry * *(..))")
//    public void declareJoinPointExpression(){}
//
//    @Before("declareJoinPointExpression()")
//    public void beforeMethod(JoinPoint point){
//        System.out.println("正在执行方法： "+point.getSignature().getName());
//    }
//
//    @After("declareJoinPointExpression()")
//    public void afterMethod(JoinPoint point){
//        System.out.println("方法执行结束： "+point.getSignature().getName());
//    }
//
//    @AfterReturning(value="declareJoinPointExpression()",returning="retVal")
//    public void afterReturningMethod(JoinPoint point,Object retVal){
//        System.out.println("方法： "+point.getSignature().getName()+"执行结果为："+retVal);
//    }
//
//    @AfterThrowing(value="declareJoinPointExpression()",throwing="ex")
//    public void afterThrowingMethod(JoinPoint point,Exception ex){
//        System.out.println("执行方法： "+point.getSignature().getName()+"出现了异常："+ex.getMessage());
//    }
//
//    @Around("declareJoinPointExpression()")
//    public Object aroundMethod(ProceedingJoinPoint point){
//
//        System.out.println("环绕通知： "+point.getSignature().getName());
//        Object result=null;
//        //这里相当于前置通知
//        try {
//            //执行方法
//            result= point.proceed();
//            //这里相当于结果通知
//        } catch (Throwable e) {
//            //这里相当于异常通知
//            e.printStackTrace();
//
//        }
//        //这里相当于后置通知
//        System.out.println("环绕通知： "+point.getSignature().getName());
//        return result;
//    }
//}