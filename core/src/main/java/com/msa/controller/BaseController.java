package com.msa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 세션 조회
     * @param key
     * @return
     */
    public Object getSession(String key) {
        log.debug("BaseController.getSession: {}", key);

        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }

        return session.getAttribute(key);
    }

    /**
     * 세션 저장
     * - 세션 매니저를 통해 세션 생성 및 회원정보 보관
     * - 세션이 있으면 세션 반환, 없으면 신규 세션 생성
     * 
     * @param key
     * @param authInfo
     */
    public void setSession(String key, Object authInfo) {
        log.debug("BaseController.setSession: {}", key);

        HttpSession session = request.getSession();
        session.setAttribute(key, authInfo);
        session.setMaxInactiveInterval(60*5); // 5분
    }

    /**
     * 세션 제거
     * @param key
     * @return
     */
    public boolean removeSession(String key) {
        log.debug("BaseController.removeSession: {}", key);

        if (this.getSession(key) == null) {
            return false;
        }

        // 해당 key 세션 제거
        request.getSession().removeAttribute(key);

        return true;
    }
}
