package com.msa.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.msa.mapper.entity.CommonCodeEntity;
import com.msa.mapper.reader.CommonCodeReaderMapper;
import com.msa.model.CommonCode;
import com.msa.model.CommonCodeList;
import com.msa.service.CommonCodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonCodeServiceImpl implements CommonCodeService {

    private final CommonCodeReaderMapper commonCodeReaderMapper;

    @Override
    public CommonCode selectCode(String codeId) {
        return CommonCode.builder().code(codeId).codeNm("type1").build();
    }

    @Override
    public List<CommonCodeList> selectCodeList() {
        int totalCount = this.commonCodeReaderMapper.selectCodeListCount();

        List<CommonCodeEntity> entity = this.commonCodeReaderMapper.selectCodeList();

        return entity.stream().map(code -> {
            return CommonCodeList.builder()
                    .codeGrp(code.getCodeGrp())
                    .code(code.getCode())
                    .codeNm(code.getCodeNm())
                    .codeVal1(code.getCodeVal1())
                    .codeVal2(code.getCodeVal2())
                    .codeVal3(code.getCodeVal3())
                    .codeVal4(code.getCodeVal4())
                    .codeVal5(code.getCodeVal5())
                    .useYn(code.getUseYn())
                    .dispNo(code.getDispNo())
                    .regDt(code.getRegDt())
                    .modDt(code.getModDt())
                    .regSeq(code.getRegSeq())
                    .modSeq(code.getModSeq())
                    .totalPage(totalCount)
                    .build();
        }).collect(Collectors.toList());
    }
}
