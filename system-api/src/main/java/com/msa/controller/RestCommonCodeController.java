package com.msa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msa.model.Result;
import com.msa.service.CommonCodeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/code")
@RequiredArgsConstructor
public class RestCommonCodeController {

    private final CommonCodeService commonCodeService;

    @GetMapping("/{codeId}")
    public Result getCode(@PathVariable("codeId") String codeId) { // pathvariable 생각
        return new Result(this.commonCodeService.selectCode(codeId));
    }

    @GetMapping("/list")
    public Result getCodeList() {
        return new Result(this.commonCodeService.selectCodeList());
    }
}
