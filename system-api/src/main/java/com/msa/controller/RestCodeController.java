package com.msa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msa.model.AdminInfo;
import com.msa.model.Code;
import com.msa.model.CodeSearch;
import com.msa.model.Result;
import com.msa.service.CodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 코드 controller
 * @author fnfnksb@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/code")
@RequiredArgsConstructor
public class RestCodeController {

    private final CodeService codeService;

    /**
     * 코드 목록 조회
     * @param param
     * @return
     */
    @GetMapping("/list")
    public Result getCodeList(CodeSearch param) {
        log.debug("RestCodeController.getCodeList: {}", param);

        return new Result(this.codeService.selectCodeList(param));
    }

    /**
     * 코드 상세정보 조회
     * @param codeGrp
     * @param code
     * @return
     */
    @GetMapping("/{codeGrp}/{code}")
    public Result getCode(@PathVariable("codeGrp") String codeGrp, @PathVariable("code") String code) { // pathvariable 생각
        log.debug("RestCodeController.getCode: {}, {}", codeGrp, code);

        return new Result(this.codeService.selectCode(codeGrp, code));
    }

    /**
     * 코드 등록
     * @param code
     * @return
     */
    @PostMapping("/")
    public Result createCode(AdminInfo adminInfo, Code codeInfo) {
        log.debug("RestCodeController.createCode: {}, {}", adminInfo, codeInfo);

        return new Result(this.codeService.insertCode(codeInfo));
    }

    /**
     * 코드 수정
     * @param code
     * @return
     */
    @PutMapping("/")
    public Result updateCode(Code codeInfo) {
        log.debug("RestCodeController.updateCode: {}", codeInfo);

        return new Result(this.codeService.updateCode(codeInfo));
    }
}
