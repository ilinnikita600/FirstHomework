package org.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


@Order
@Component
@Aspect
public class LoggingAspect {
    private final Logger sessionLogger;
    LoggingAspect(@Qualifier("sessionFileLogger") Logger logger) {
        sessionLogger = logger;
    }

    @Around("@target(org.example.annotations.Loggable)")
    public Object logBefore(ProceedingJoinPoint joinPoint) {
        sessionLogger.log(Level.INFO, "Method: " + joinPoint.getSignature() + " Args: " + Arrays.toString(joinPoint.getArgs()));
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
