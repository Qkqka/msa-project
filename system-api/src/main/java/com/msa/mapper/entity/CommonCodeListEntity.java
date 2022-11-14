package com.msa.mapper.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class CommonCodeListEntity {
    private String codeGrp;
    private String code;
    private String codeNm;
    private String codeVal1;
    private String codeVal2;
    private String codeVal3;
    private String codeVal4;
    private String codeVal5;
    private String useYn;
    private String dispNo;
    private String regDt;
    private String modDt;
    private String regSeq;
    private String modSeq;
}