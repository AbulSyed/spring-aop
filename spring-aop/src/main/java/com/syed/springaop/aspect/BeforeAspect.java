package com.syed.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class BeforeAspect {

    private static final Logger log = LoggerFactory.getLogger(BeforeAspect.class);

    // pointcut expression
    // @Before("execution(public void com.syed.springaop.service.AccountService.addAccount())")
    // @Before("execution(void com.syed.springaop.service.AccountService.addAccount())")
    // @Before("execution(* add*())")
    // @Before("execution(* com.syed.springaop.service.*.*())") // any class & method in package
    // @Before("execution(* com.syed.springaop.service.*.*(..))") any class & method in package & method with 0 or more arguments of any type

//    @Before("execution(* add*())")
//    @Before("execution(* addAccount(*))")
//    @Before("execution(* com.syed.springaop.service.*.*(..))")
    @Before("com.syed.springaop.aspect.expressions.AopExpressions.forAllPackagesExceptGetterAndSetter()")
    public void loggingAspect(JoinPoint joinPoint) {
        log.info("Entering {}", joinPoint.getSignature().toShortString());
        Object[] args = joinPoint.getArgs();

        for (Object o : args) {
            System.out.println(o);
        }
    }
}
