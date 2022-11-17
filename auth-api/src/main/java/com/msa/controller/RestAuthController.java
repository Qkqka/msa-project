package com.msa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msa.model.AuthInfo;
import com.msa.model.Result;
import com.msa.service.AuthService;
import com.msa.util.CommonCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor // inject?
public class RestAuthController extends BaseController {

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
    public Result<?> login(@RequestParam("id") String id, @RequestParam("password") String password) {
        log.info("RestAuthController.login id: {}");

        // 사용자정보 조회
        AuthInfo userInfo = authService.selectAdminInfo(id, password);
        if (userInfo == null) {
            return new Result<>();
        }

        super.setSession(CommonCode.LOGIN_SESSION, userInfo);

        log.info("RestAuthController.login: {}", userInfo);

        return new Result<>(userInfo);
    }

    // 로그인 ㅅㅔ션 정보만 만료
    @PostMapping("/logout")
    public Result<?> logout() {
        if (super.removeSession(CommonCode.LOGIN_SESSION)) {
            return new Result<>("not login");
        }

        return new Result<>("logout");
    }

    /**
     * 인증 정보 체크 
     *  - 세션 정보
     *  - 권한 관리 redirectUrl
     * @return
     */
    @GetMapping("/check")
    public Result<?> authCheck(String redirectUrl) {
        log.debug("RestAuthController.authCheck()");

        if (super.getSession(CommonCode.LOGIN_SESSION) == null) {
            return new Result<>(-1);
        }

        return new Result<>(0);
    }
}
