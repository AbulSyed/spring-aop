package com.syed.springaop.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Aspect
@Component
public class AfterThrowingAspect {

    private static final Logger log = LoggerFactory.getLogger(AfterThrowingAspect.class);

    @AfterThrowing(
            pointcut = "com.syed.springaop.aspect.expressions.AopExpressions.forSimulateException()",
            throwing = "exc"
    )
    public void afterTrowingAdvice(Throwable exc) {
        log.info("in afterTrowingAdvice {}", exc.getMessage());
    }
}
