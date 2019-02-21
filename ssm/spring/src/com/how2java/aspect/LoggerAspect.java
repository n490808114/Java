package com.how2java.aspect;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Timer;

public class LoggerAspect {
    public Object log(ProceedingJoinPoint joinPoint)throws Throwable{
        System.out.println("Start log");

        Object object = joinPoint.proceed();

        System.out.println("End log:" +joinPoint.getSignature().getName());
        return object;
    }
}
