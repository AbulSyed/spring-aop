package com.syed.springaop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Aspect
@Component
public class AfterAspect {

    private static final Logger log = LoggerFactory.getLogger(AfterAspect.class);

    @After("com.syed.springaop.aspect.expressions.AopExpressions.forSimulateException()")
    public void afterAspectAdvice() {
        log.info("afterAspectAdvice will run regardless of method outcome");
    }
}
