package com.example.springdemo.springaopdemo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)//The order annotation allows us to control the order of when certain aspects are run
//Lower numbers have higher priority

public class ApiAnalyticsAspect {

    Logger logger = LoggerFactory.getLogger(ApiAnalyticsAspect.class);

    //This expression matches any method in the springaopdemo subpackages
    @Pointcut("execution( * com.example.springdemo.springaopdemo.*.*.*(..)   )")
    private void forDaoPackage() {
        //This is just used for the Pointcut expression
    }


    //This expression matches any method that starts with set in the springaopdemo subpackages
    @Pointcut(" execution(* com.example.springdemo.springaopdemo.*.*.set*(..)  )")
    private void forSetters() {
        //This is just used for the Pointcut expression
    }


    //This expression matches any method that starts with get in the springaopdemo subpackages
    @Pointcut(" execution(* com.example.springdemo.springaopdemo.*.*.get*(..)  )")
    private void forGetters() {
        //This is just used for the Pointcut expression
    }

    @Before("forSetters()")
    private void setterTester() {
        logger.info("==============> @Before setter method from any class");
    }

    @Before("forGetters()")
    private void getterTester() {
        logger.info("==============> @Before getter method from any class");
    }

    //Example of combining multiple pointcut expressions within a single advice
    @Before("forDaoPackage() && !(forSetters() || forGetters())")
    public void performApiAnalytics() {
        logger.info("=================> Performing API Analytics");
    }

}
