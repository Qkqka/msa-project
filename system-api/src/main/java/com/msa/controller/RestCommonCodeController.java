package com.msa.controller;

import com.msa.model.CommonCode;
import com.msa.service.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RestCommonCodeController {

    private final CommonCodeService commonCodeService;

    @GetMapping("/{codeId}")
    public CommonCode getCode(@PathVariable("codeId") String codeId) {
        return this.commonCodeService.selectCode(codeId);
    }
}
