package com.msa.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msa.config.ApplicationYAMLConfig;
import com.msa.model.AdminInfo;
import com.msa.model.Result;
import com.msa.service.AuthService;
import com.msa.util.CommonUtil;

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
     * @param id
     * @param password
     * @return
     */
    @GetMapping("/login")
    public Result login(@RequestParam("id") String id, @RequestParam("password") String password) {
        log.info("RestAuthController.login.id: {}", id);

        // 필수값 체크
        CommonUtil.checkParam(id, password);

        // 관리자정보 조회
        AdminInfo userInfo = authService.selectAdminInfo(id, password);
        if (userInfo == null) {
            return new Result();
        }

        super.setSession(this.applicationYAMLConfig.getSession().getKey(), userInfo);

        log.info("RestAuthController.login: {}", userInfo);

        return new Result(userInfo);
    }

    /**
     * 로그아웃
     * @return
     */
    @GetMapping("/logout")
    public Result logout() {
        // 로그인 ㅅㅔ션 정보만 만료
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
        log.debug("RestAuthController.authCheck: {}", super.getSession(this.applicationYAMLConfig.getSession().getKey()));

        AdminInfo adminInfo = (AdminInfo) super.getSession(this.applicationYAMLConfig.getSession().getKey());

        // 로그인 체크
        if (adminInfo == null) {
            return new Result(-1, "로그인해주세요.");
        }

//        // 권한 체크 api
//        if (!adminInfo.getAdminMenuList().stream().anyMatch(adminMenu -> StringUtils.equals(adminMenu.getMenuUrl(), api))) {
//            return new Result(-2, "해당 메뉴에 접근 권한이 없습니다.");
//        }

        return new Result();
    }
}
