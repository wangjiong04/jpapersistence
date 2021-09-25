package com.example.jpapersistence.measure;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.Method;

@Log4j2
@Aspect
@Configuration
@EnableAspectJAutoProxy
public class MeasuredAspect {
    @Around("@annotation(com.example.jpapersistence.measure.Measured)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Measured measured = method.getAnnotation(Measured.class);
        String message = measured.message();

        if (StringUtils.isEmpty(message))
            log.info("Method {} execution: {} ms", joinPoint.getSignature().toShortString(), executionTime);
        else
            log.info("{}: {} ms", message, executionTime);
        return proceed;
    }
}