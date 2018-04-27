package com.gthoya.springmvc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.time.LocalDate;

@Aspect
@Slf4j
public class TimeLoggingAspect {
    @Around("execution(public * com.gthoya.springmvc..dao.*DAO.*(..))")
    public Object executeQueryTimeLog(ProceedingJoinPoint joinpoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.debug("###################### start time = {} ######################", startTime);

        Object obj = joinpoint.proceed();

        long endTime = System.currentTimeMillis();
        log.debug("###################### end time = {} ######################", endTime);
        log.debug("###################### execute query time = {}ms ######################", endTime - startTime);

        return obj;
    }
}
