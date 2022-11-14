package com.msa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msa.model.CommonCode;
import com.msa.model.CommonCodeList;
import com.msa.service.CommonCodeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RestCommonCodeController {

    private final CommonCodeService commonCodeService;

    @GetMapping("/code/{codeId}")
    public CommonCode getCode(@PathVariable("codeId") String codeId) {
        return this.commonCodeService.selectCode(codeId);
    }

    @GetMapping("/code/list")
    public List<CommonCodeList> getCodeList() {
        return this.commonCodeService.selectCodeList();
    }
}
