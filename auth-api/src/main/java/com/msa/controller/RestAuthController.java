package com.msa.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msa.config.ApplicationYAMLConfig;
import com.msa.model.AuthInfo;
import com.msa.model.Result;
import com.msa.service.AuthService;
import com.msa.util.CommonController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 인증 controller
 * @author fnfnksb@gmail.com
 */
@Slf4j
@RestController
@RequiredArgsConstructor // @inject, @autowired
public class RestAuthController extends CommonController {

    // @inject
    private final Environment env;

    private final AuthService authService;

    private final ApplicationYAMLConfig applicationYAMLConfig;

    @GetMapping("/health_check")
    public String status() {
        return String.format("auth-api health check ==== PORT : %s ", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome(@RequestHeader("auth-api-request") String header) {
        log.info("header : {}", header);
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
    public Result login(@RequestParam("id") String id, @RequestParam("password") String password) {
        log.info("RestAuthController.login id: {}");

        // 관리자정보 조회
        AuthInfo userInfo = authService.selectAdminInfo(id, password);
        if (userInfo == null) {
            return new Result();
        }

        super.setSession(this.applicationYAMLConfig.getSession().getKey(), userInfo);

        log.info("RestAuthController.login: {}", userInfo);

        return new Result(userInfo);
    }

    // 로그인 ㅅㅔ션 정보만 만료
    @GetMapping("/logout")
    public Result logout() {
        if (super.removeSession(this.applicationYAMLConfig.getSession().getKey())) {
            return new Result("not login");
        }

        return new Result("logout");
    }

    /**
     * 인증 정보 체크 
     *  - 세션 정보
     *  - 권한 관리 api
     * @return
     */
    @GetMapping("/check")
    public Result authCheck(String api) {
        log.debug("RestAuthController.authCheck");

        // 로그인 체크
        if (super.getSession(this.applicationYAMLConfig.getSession().getKey()) == null) {
            return new Result(-1, "로그인해주세요.");
        }

        // 권한 체크 api
//        if (true) {
//            return new Result(-2);
//        }

        return new Result();
    }
}
