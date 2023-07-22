package com.syed.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(5)
@Aspect
@Component
public class AroundAspect {

    private static final Logger log = LoggerFactory.getLogger(AroundAspect.class);

    // method that is intercepted could return anything
    @Around("com.syed.springaop.aspect.expressions.AopExpressions.forTimeMethod()")
    public Object aroundAspectAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();

        log.info("aroundAspectAdvice calculated duration of method {}", end - start);

        return result;
    }

}
