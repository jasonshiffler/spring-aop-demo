package com.example.springdemo.springaopdemo.aop;

import com.example.springdemo.springaopdemo.entities.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


@Aspect
@Component
@Order(2) //The order annotation allows us to control the order of when certain aspects are run
public class DemoLoggingAspect {


    //The logger we'll use to generate logging info

    Logger logger = LoggerFactory.getLogger(DemoLoggingAspect.class);
    
    
    //Works for a method signature from any class
    @Before("execution (public void addAccount(..))")

    //The JoinPoint object gives us info about the method call
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){

        logger.info("==============> @Before public void addAccount method from any class");

        MethodSignature methSig = (MethodSignature) theJoinPoint.getSignature();

        //Display the method signature
        logger.info("Method: {}" , methSig);

        //Display the method arguments
        Object[] args = theJoinPoint.getArgs();

        int i = 0;
        for(Object tempArg: args){
            tempArg.getClass();
            logger.info("Argument {} is {} " ,i, tempArg);
            i++;
        }
    }

//Works for a method signature from any class with a void return type

    @Before("execution (void addAccount())")
    public void beforeVoidAddAccountAdvice(){
        logger.info("==============> @Before void addAccount method from any class");
    }

    //Works for a method signature from a specific class

    @Before("execution (public void com.example.springdemo.springaopdemo.dao.AccountDAO.addAccount())")
    public void beforeAddAccountAdviceSpecificClass(){
        logger.info("==============> @Before public void addAccount method from specific class");
    }

    //Works for a method signature with from any class that starts with add

    @Before("execution (public void add**())")
    public void beforeAnyAddAdvice(){
        logger.info("==============> @Before any public void method starting with add from any class");
    }

    //The Pointcut annotation allows us to reuse a matching expression in multiple places so we don't have to
    //copy it.
    @Pointcut("execution (public void delete**() )")
    public void deletePointCut(){
        //Just being used to define the pointcut for other other annotations like @Before to use

    }

    //We're reusing the pointcut expression that was declared above.
    @Before("deletePointCut()")
    public void loggingMethod(){
        logger.info("in the Logging method");
    }

    //AfterReturning allows us to run a method after a matching pointcut method runs. We can then intercept the returned
    //value, examine it, and even modify it if we want to.
    //Only runs if the method execution is successful
    @AfterReturning(pointcut = "execution(* com.example.springdemo.springaopdemo.dao.AccountDAO.findAccounts(..))",
    returning = "accounts")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> accounts){

        //Turn the method signature into a string so we can print it.
        String method = theJoinPoint.getSignature().toString();

        logger.info("============>>>Executing @AfterReturning on method: {}", method );


        //It is possible to modify the returned value that has been intercepted but this should be used with caution
        for(Account account: accounts){
            logger.info(account.toString());
        }
    } //close method

    //The AfterThrowing allows us to intercept an exception thrown by a method. The exception is still sent up the call
    //stack however.
    @AfterThrowing(pointcut = "execution(* com.example.springdemo.springaopdemo.dao.AccountDAO.*(..))",
            throwing="except")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Exception except){

        MethodSignature theMethod = (MethodSignature)theJoinPoint.getSignature();
        //Display the method signature
        logger.info("===============>Executing @AfterThrowing on method: {} ", theMethod);
        logger.info("===============>The Exception is ", except);
    }

    //@After advice runs regardless if the method runs successfully or not, it does not have access to any exceptions
    @After("execution(* com.example.springdemo.springaopdemo.dao.AccountDAO.*(..))")
    public void afterDemo(JoinPoint theJoinPoint){

        String method = theJoinPoint.getSignature().toShortString();

        logger.info("&&&&&&&&&Executing @After advice on method: {} &&&&&&&",method);

    }

    //The Around advice runs both before and after a method call
    @Around("execution(* com.example.springdemo.springaopdemo.dao.AccountDAO.addAccountAround*(..))")
    public Object aroundTester(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{


        Object result = null;

        String method = theProceedingJoinPoint.getSignature().toShortString();
        logger.info("^^^^^^^^^^^Executing @Around advice on method: {} ^^^^^^^^^^^^^", method);

        long begin = System.currentTimeMillis();

        //Run the Method we've intercepted

        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Exception e) {
              logger.warn("@Around advice problem {}" , e);
              // throw e; we can rethrow the exception if we want the main program to handle it.
        }

        long end = System.currentTimeMillis();

        long duration = end - begin;

        logger.info("==========> Duration: {}  seconds", duration/ 1000.0 );

        return result;
    }
}