package com.msa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msa.mapper.reader.CommonCodeReaderMapper;
import com.msa.model.CommonCode;
import com.msa.model.CommonCodeList;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonCodeService {

    private final CommonCodeReaderMapper commonCodeReaderMapper;

    public CommonCode selectCode(String codeId) {
        return CommonCode.builder().code(codeId).codeNm("type1").build();
    }

    public List<CommonCodeList> selectCodeList() {
        int totalCount = this.commonCodeReaderMapper.selectCodeListCount();

        return this.commonCodeReaderMapper.selectCodeList();
    }
}
