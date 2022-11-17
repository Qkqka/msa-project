package com.msa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public Object getSession(String key) {
        HttpSession session = request.getSession();
        log.debug("BaseController.getSession: {}", session);
        return session.getAttribute(key);
    }

    public void setSession(String key, Object authInfo) {
        // 세션 매니저를 통해 세션 생성 및 회원정보 보관
        // 세션이 있으면 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(key, authInfo);
        session.setMaxInactiveInterval(60*5); // 5분

        log.info("session.attribute: {}", session.getAttribute(key));
        log.info("session.attribute.maxInactiveInterval: {}", session.getMaxInactiveInterval());
    }

    public boolean removeSession(String key) {
        Object authInfo = this.getSession(key);

        if (authInfo == null) {
            return false;
        }

        request.getSession().removeAttribute(key);

        return true;
    }
}
