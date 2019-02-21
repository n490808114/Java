package com.how2java.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class Timer {
    public Object costTimer(ProceedingJoinPoint joinPoint)throws Throwable{
        long startTime = System.currentTimeMillis();
        System.out.println("Cost Timer Start! at:"+startTime);
        startTime = System.nanoTime();

        Object object = joinPoint.proceed();

        long endTime = System.nanoTime();
        long costTime = (endTime - startTime)/1000;
        endTime = System.currentTimeMillis();
        System.out.println("Cost Timer End! at"+endTime);
        System.out.println("Cost Time:"+costTime);
        return object;
    }
}
