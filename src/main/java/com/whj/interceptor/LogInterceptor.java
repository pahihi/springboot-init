package com.whj.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@EnableAspectJAutoProxy
public class LogInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LogAnnotation.class);

    // @Pointcut("@annotation(com.whj.interceptor.LogAnnotation)")
    // public void annotationPointcut() {
    // }
    //
    // @Before("annotationPointcut()")
    // public void beforeLog(JoinPoint joinPoint) {
    //     MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    //     LogAnnotation annotation = signature.getMethod().getAnnotation(LogAnnotation.class);
    //     if (Objects.nonNull(annotation)) {
    //         log.info("before interceptor :" + annotation.value());
    //     }
    // }
    //
    // @After("annotationPointcut()")
    // public void after(JoinPoint joinPoint) {
    //     log.info("调用了后置通知");
    // }
    //
    // @AfterThrowing(value = "annotationPointcut()", throwing = "throwable")
    // public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
    //     log.info("调用了异常通知:" + throwable.getCause());
    // }
    //
    // @AfterReturning(value = "annotationPointcut()", returning = "result")
    // public void afterReturningMethod(JoinPoint joinPoint, Object result) {
    //     log.info("调用了返回通知函数");
    // }

    @Around("@annotation(LogAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LogAnnotation annotation = signature.getMethod().getAnnotation(LogAnnotation.class);
        if (Objects.nonNull(annotation)) {
            log.info("方法执行之前  :" + annotation.value());
        }
        Object object = joinPoint.proceed();
        log.info("around 执行方法之后--返回值：" + object);
        return object;
    }
}
