package com.msa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();

        // 세션에 로그인 정보가 있다면 그대로 진행
        if (session.getAttribute("authInfo") != null) {
            return true;

        // 없으면 로그인 페이지로 이동
        } else {
            // 로그인 성공 후 다시 원래의 목적지로 갈 수 있게 prev 정보 저장
//            response.sendRedirect(request.getContextPath() + "login?prev=" + request.getServletPath());
            log.debug("request.getServletPath: {}", request.getServletPath());
            response.sendRedirect("localhost:9100/login?prev=" + request.getServletPath());
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
