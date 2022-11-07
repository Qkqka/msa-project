package com.msa.controller;

import com.msa.domain.AuthInfo;
import com.msa.domain.Manager;
import com.msa.domain.ManagerLogin;
import com.msa.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RestAuthController {

    private final Environment env;

    private final AuthService authService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("auth-api health check ==== PORT : %s ", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome( @RequestHeader("auth-api-request") String header, HttpServletRequest request) {
        log.info("header : {}", header);
        log.info("request.serverPort : {}", request.getServerPort());
        log.info("env.server.port : {}", env.getProperty("local.server.port"));

        return env.getProperty("greeting.message");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @RequestBody ManagerLogin loginInfo) {
        // getSession(true) 를 사용하면 처음 들어온 사용자도 세션이 만들어지기 때문에 false로 받음
        HttpSession session = request.getSession(false);
        log.info("session.authInfo: {}", session.getAttribute("authInfo"));
        log.info("RestAuthController.login: {}", loginInfo);
        AuthInfo authInfo = authService.login(loginInfo);
        if (authInfo == null) {
            return null;
        }

        log.info("RestAuthController.login: {}", authInfo);
        session.setAttribute("authInfo", authInfo);
        session.setMaxInactiveInterval(60*5*1000); // 5분

        log.info("session.authInfo: {}", session.getAttribute("authInfo"));
        log.info("session.authInfo: {}", session.getMaxInactiveInterval());

        //쿠키에 시간 정보를 주지 않으면 세션 쿠기(브라우저 종료시 모두 종료)
        Cookie cookie = new Cookie("mySessionId", session.getId());
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).body(authInfo);
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        log.info("session.authInfo: {}", session.getAttribute("authInfo"));
        session.invalidate();
        return "logout";
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Manager> getUser(@PathVariable("userId") String userId) {
        log.info("RestAuthController.getUser: {}", userId);

        return ResponseEntity.status(HttpStatus.OK).body(authService.getUser(userId));
    }
}
