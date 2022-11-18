package com.msa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msa.model.AuthInfo;
import com.msa.model.Code;
import com.msa.model.CommonCodeSearch;
import com.msa.model.Result;
import com.msa.service.CommonCodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 공통코드 controller
 * @author fnfnksb@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/code")
@RequiredArgsConstructor
public class RestCommonCodeController {

    private final CommonCodeService commonCodeService;

    /**
     * 공통코드 목록 조회
     * @param param
     * @return
     */
    @GetMapping("/list")
    public Result getCodeList(CommonCodeSearch param) {
        log.info("RestCommonCodeController.getCodeList : {}", param);
        return new Result(this.commonCodeService.selectCodeList(param));
    }

    /**
     * 공통코드 상세정보 조회
     * @param codeGrp
     * @param code
     * @return
     */
    @GetMapping("/{codeGrp}/{code}")
    public Result getCode(@PathVariable("codeGrp") String codeGrp, @PathVariable("code") String code) { // pathvariable 생각
        log.info("RestCommonCodeController.getCode : {}.{}", codeGrp, code);
        return new Result(this.commonCodeService.selectCode(codeGrp, code));
    }

    /**
     * 공통코드 등록
     * @param commonCode
     * @return
     */
    @PostMapping("/")
    public Result createCode(AuthInfo authInfo, Code commonCode) {
        log.info("RestCommonCodeController.createCode: {}, {}", authInfo, commonCode);
        return new Result(this.commonCodeService.insertCommonCode(commonCode));
    }

    /**
     * 공통코드 수정
     * @param commonCode
     * @return
     */
    @PutMapping("/")
    public Result updateCode(Code commonCode) {
        log.info("RestCommonCodeController.updateCode: {}", commonCode);
        return new Result(this.commonCodeService.updateCommonCode(commonCode));
    }
}
