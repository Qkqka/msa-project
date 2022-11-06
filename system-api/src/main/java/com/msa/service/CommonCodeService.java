package com.msa.service;

import com.msa.domain.CommonCode;
import org.springframework.stereotype.Service;

@Service
public class CommonCodeService {
    public CommonCode selectCode(String codeId) {
        return CommonCode.builder().codeId(codeId).codeName("type1").build();
    }
}
