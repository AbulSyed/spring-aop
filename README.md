## Aspect-oriented programming

AOP is a methodology where you seperate your cross cutting concerns from your main business logic.

### Use cases

- Most common: logging, security, transactions
- Audit logging: who, what, when where
- Exception handling: log exception
- API management: how many times a method has been called by a user & analytics

### Terminology

- Aspect: module of code for a cross cutting concern (logging, security...)
- Advice: what action is taken & when is should be applied
  1. Before advice - run before method
  2. After advice - run after both success & failure of a method
  3. After returning advice - run after method (success)
  4. After throwing advice - run after method (if exception thrown)
  5. Around advice - run before & after method
- Join Point: when to apply code during program execution
- Pointcut: predicate expression for where the advice should be applied

### Dependency

```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

### @Before

execution(`modifier`? `return type` `class name`? `method name(params)` `throws`?)

- ? are optional

```
@Before("execution(public void com.syed.springaop.service.AccountService.addAccount())")
```

- * wildcard to match anything
- modifier is optional & must be `public`, so let's drop it

```
@Before("execution(void com.syed.springaop.service.AccountService.add*())")
```

- expression that returns `void` & in class `AccountService` with method name that starts with `add`

```
@Before("execution(* com.syed.springaop.service.AccountService.add*())")
```

- we made return type optional

```
@Before("execution(* com.syed.springaop.service.*.*())")
```

- any class & method in package

### method parameter matching

`()` - no arguments
`(*)` - method with 1 argument of any type
`(com.syed.entity.Account)` - method with 1 argument of Account type
`(..)` - method with 0 or more arguments of any type

```
@Before("execution(* com.syed.springaop.service.*.*(..))")
// any class & method in package & method with 0 or more arguments of any type
```

### reuse pointcut expression

```
@Aspect
@Component
public class LoggingAspect {

  private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("execution(* com.syed.springaop.service.*.*(..))")
  private void forAllPackages() {}

  @Before("forAllPackages()")
  public void loggingAspect() {
    log.info("logging aspect");
  }
}
```

### pointcut conditions

```
@Pointcut("execution(* com.syed.springaop.service.*.*(..))")
private void forAllPackages() {}

@Pointcut("execution(* com.syed.springaop.service.*.get*(..))")
private void getter() {}

@Pointcut("execution(* com.syed.springaop.service.*.set*(..))")
private void setter() {}

@Pointcut("forAllPackages() && !(getter() || setter())")
private void forAllPackagesExceptGetterAndSetter() {}

@Before("forAllPackagesExceptGetterAndSetter()")
public void loggingAspect() {
  log.info("logging aspect");
}
```

### order of aspects

add @Order(1) on the aspect you want to run first

### JoinPoint

JoinPoint can be used to get method signature & arguments

```
@Before("com.syed.springaop.aspect.AopExpressions.forAllPackagesExceptGetterAndSetter()")
public void loggingAspect(JoinPoint joinPoint) {
  log.info("Entering {}", joinPoint.getSignature());
  Object[] args = joinPoint.getArgs();

  for (Object o : args) {
    System.out.println(o);
  }
}
```

### @AfterReturning advice

runs after successful execution of a method

```
@AfterReturning(
  pointcut = "com.syed.springaop.aspect.AopExpressions.forGetAllAccounts()",
  returning = "result"
)
public void afterGetAllAccounts(List<String> result) {
  log.info("After AccountService:getAllAccounts");
  log.info("AfterReturning Result {}", result);
}
```

### @AfterThrowing advice

will be executed before the exception is caught

```
@AfterThrowing(
  pointcut = "com.syed.springaop.aspect.AopExpressions.forSimulateException()",
  throwing = "exc"
)
public void afterTrowingAdvice(Throwable exc) {
  log.info("in afterTrowingAdvice {}", exc.getMessage());
}
```

### @After

will run after both success & failure

### @Around

runs before & after

- can be used to check how long a method ran
- proceeding join point to execute the target method

```
// method that is intercepted could return anything
@Around("com.syed.springaop.aspect.expressions.AopExpressions.forTimeMethod()")
public Object aroundAspectAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
  long start = System.currentTimeMillis();

  // without below method will not be called
  Object result = proceedingJoinPoint.proceed();

  long end = System.currentTimeMillis();

  log.info("aroundAspectAdvice calculated duration of method {}", end - start);

  return result;
}
```
