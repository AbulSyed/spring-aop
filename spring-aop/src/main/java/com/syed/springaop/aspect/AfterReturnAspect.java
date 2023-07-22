package com.syed.springaop.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(2)
@Aspect
@Component
public class AfterReturnAspect {

    private static final Logger log = LoggerFactory.getLogger(AfterReturnAspect.class);

    @AfterReturning(
            pointcut = "com.syed.springaop.aspect.expressions.AopExpressions.forGetAllAccounts()",
            returning = "result"
    )
    public void afterGetAllAccounts(List<String> result) {
        log.info("After AccountService:getAllAccounts");
        log.info("AfterReturning Result {}", result);
    }
}
