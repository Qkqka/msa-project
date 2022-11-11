package com.msa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msa.domain.AuthInfo;
import com.msa.model.ManagerLogin;
import com.msa.service.AuthService;
import com.msa.util.CommonCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor // inject?
public class RestAuthController {

    // @inject
    private final Environment env;

    private final AuthService authService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("auth-api health check ==== PORT : %s ", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome(@RequestHeader("auth-api-request") String header, HttpServletRequest request) {
        log.info("header : {}", header);
        log.info("request.serverPort : {}", request.getServerPort());
        log.info("env.server.port : {}", env.getProperty("local.server.port"));

        return env.getProperty("greeting.message");
    }

    /**
     * request, response 공통으로 빼는
     * getmapping/postmapping/requestmapping
     * @requestbody 언제 쓸지 생각해보기(필수값 체크를 못한다.)
     * 개발패턴 일반화
     * 응답결과 통일
     * 
     * @param request
     * @param response
     * @param loginInfo
     * @return
     */
    @GetMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @Valid ManagerLogin loginInfo) {

        // 사용자정보 조회
        AuthInfo userInfo = authService.login(loginInfo);
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        log.info("RestAuthController.login: {}", userInfo);

        // 세션 매니저를 통해 세션 생성 및 회원정보 보관
        // 세션이 있으면 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(CommonCode.LOGIN_SESSION, userInfo);
        session.setMaxInactiveInterval(60*5); // 5분

        log.info("session.authInfo: {}", session.getAttribute(CommonCode.LOGIN_SESSION));
        log.info("session.authInfo: {}", session.getMaxInactiveInterval());

        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }

    // 로그인 ㅅㅔ션 정보만 만료
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("session.authInfo: {}", session.getAttribute("authInfo"));
        if (session.getAttribute("authInfo") == null) {
            return "not login";
        }
        session.removeAttribute("authInfo");
        return "logout";
    }
}
