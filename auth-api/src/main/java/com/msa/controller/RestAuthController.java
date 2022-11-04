package com.msa.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msa.domain.Manager;
import com.msa.domain.ManagerLogin;
import com.msa.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    public ResponseEntity<?> login(HttpSession session, @RequestBody ManagerLogin loginInfo) {
        log.info("RestAuthController.login: {}", loginInfo);
        Manager managerInfo = authService.login(loginInfo);
        if (managerInfo == null) {
            return null;
        }

        log.info("RestAuthController.login: {}", managerInfo);
        session.setAttribute("userId", UUID.randomUUID().toString());
        session.setMaxInactiveInterval(60*5); // 5분

//        return response.success(tokenInfo, "로그인에 성공했습니다.", HttpStatus.OK);
        return null;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "logout";
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Manager> getUser(@PathVariable("userId") String userId) {
        log.info("RestAuthController.getUser: {}", userId);

        return ResponseEntity.status(HttpStatus.OK).body(authService.getUser(userId));
    }
}
