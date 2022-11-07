package com.msa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PerformanceInterceptor implements HandlerInterceptor {

    // return true인 경우 실제 handler 호출, false인 경우 중지
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTiem", startTime);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    // handler 동작이 종료된 후 호출
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        //HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        long midTime = System.currentTimeMillis();
        // 정적 리소스(css,js,..)은 modelAndView null
        if(modelAndView != null) {
            // modelAndView를 view에 전달할 자료의 수정 가능
        }
        request.setAttribute("midTime", midTime);
    }

    // view에 대한 rendering까지 종료된 후 호출
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        long endTime = System.currentTimeMillis();
        Object midTimeObj = request.getAttribute("midTime");
        Object startTimeObj = request.getAttribute("startTiem");
        log.trace("servlet: {}", request.getServletPath());
        if (midTimeObj != null) {
            log.trace("Handler 동작시간: {}", endTime - (Long) midTimeObj);
        }
        log.trace("전체 동작시간: {}", endTime - (Long) startTimeObj);
    }

}
