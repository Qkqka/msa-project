package com.msa.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.msa.model.ApiLogEvent;
import com.msa.util.ClientUtil;
import com.msa.wrapper.HttpServletRequestBodyWrapper;
import com.msa.wrapper.HttpServletResponseBodyWrapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그 등록
 * @author fnfnksb@gmail.com
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAop {

    private final ApplicationContext applicationContext;

    @Around("execution(public * com..controller.*.*(..))") // com.msa.controller 패키지 밑에 있는 모든 메소드
    public Object aroundLog(ProceedingJoinPoint joinPoint) {
        log.info("Before method called. {}", joinPoint.getSignature().toString());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        HttpServletRequestBodyWrapper requestWrapper = null;
        HttpServletResponseBodyWrapper responseWrapper = null;

        Object result = null;
        String requestMethod = request.getMethod();
        String requestUri = request.getRequestURI();
        String successYn = "Y";
        long execTime;
        String requestParam = null;
        String requestBody = null;
        String responseResult = null;
        String errMsg = null;
        String errLog = null;
        String clientIp = ClientUtil.getRemoteAddr(request);
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);

        StopWatch sw = new StopWatch();
        try {
            sw.start();
            if (request instanceof HttpServletRequestBodyWrapper) {
                requestWrapper = new HttpServletRequestBodyWrapper(request);
                requestParam = requestWrapper.getParamJson();
                requestBody = requestWrapper.getBodyString();
            }
            if (response instanceof HttpServletResponseBodyWrapper) {
                responseWrapper = new HttpServletResponseBodyWrapper(response);
                responseResult = responseWrapper.getBodyString();
            }

            result = joinPoint.proceed();
            log.info("After method called with result => {}", result);

        } catch (Throwable e) {
            successYn = "N";
            errMsg = e.getMessage();
            errLog = e.toString();
        } finally {
            sw.stop();
            execTime = sw.getTotalTimeMillis();
            this.applicationContext.publishEvent(new ApiLogEvent(requestMethod, requestUri, successYn, execTime, requestParam, requestBody, responseResult, errMsg, errLog, clientIp, userAgent));
        }

        return result;
    }
}
