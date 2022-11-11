package com.msa.service;

import org.springframework.stereotype.Service;

import com.msa.model.CommonCode;

@Service
public class CommonCodeService {
    public CommonCode selectCode(String codeId) {
        return CommonCode.builder().codeId(codeId).codeName("type1").build();
    }
}
