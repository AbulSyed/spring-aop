package com.syed.springaop.aspect.expressions;

import org.aspectj.lang.annotation.Pointcut;

public class AopExpressions {

    @Pointcut("execution(* com.syed.springaop.service.*.*(..))")
    public void forAllPackages() {}

    @Pointcut("execution(* com.syed.springaop.service.*.get*(..))")
    public void getter() {}

    @Pointcut("execution(* com.syed.springaop.service.*.set*(..))")
    public void setter() {}

    @Pointcut("forAllPackages() && !(getter() || setter())")
    public void forAllPackagesExceptGetterAndSetter() {}

    @Pointcut("execution(* com.syed.springaop.service.AccountService.getAllAccounts())")
    public void forGetAllAccounts() {}

    @Pointcut("execution(* com.syed.springaop.service.AccountService.simulateException())")
    public void forSimulateException() {}

    @Pointcut("execution(* com.syed.springaop.service.AccountService.timeMethod())")
    public void forTimeMethod() {}
}
