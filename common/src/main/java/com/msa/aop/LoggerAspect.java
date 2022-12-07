package com.msa.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(public * com..controller.*.*(..))") // com.msa.controller 패키지 밑에 있는 모든 메소드
    public Object beforeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before method called. {}", joinPoint.getSignature().toString());
        Object result = joinPoint.proceed();
        log.info("After method called with result => {}", result);

        return result;
    }
}