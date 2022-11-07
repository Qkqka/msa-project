package com.msa.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msa.domain.AuthInfo;
import com.msa.domain.ManagerLogin;
import com.msa.service.AuthService;

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
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @RequestBody ManagerLogin loginInfo) {
        // getSession(true) 를 사용하면 처음 들어온 사용자도 세션이 만들어지기 때문에 false로 받음
        HttpSession session = request.getSession();
        //log.info("session.authInfo: {}", session.getAttribute("authInfo"));
        //log.info("RestAuthController.login: {}", loginInfo);
        AuthInfo authInfo = authService.login(loginInfo);
        if (authInfo == null) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        log.info("RestAuthController.login: {}", authInfo);
        session.setAttribute("authInfo", authInfo);
        session.setMaxInactiveInterval(60*5*1000); // 5분

        log.info("session.authInfo: {}", session.getAttribute("authInfo"));
        log.info("session.authInfo: {}", session.getMaxInactiveInterval());

//        쿠키에 시간 정보를 주지 않으면 세션 쿠기(브라우저 종료료시 모두 종료)
//        Cookie cookie = new Cookie("mySessionId", session.getId());
//        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).body(authInfo);
    }

    // 로그인 ㅅㅔ션 정보만 만료
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("session.authInfo: {}", session.getAttribute("authInfo"));
        session.invalidate();
        return "logout";
    }
}
