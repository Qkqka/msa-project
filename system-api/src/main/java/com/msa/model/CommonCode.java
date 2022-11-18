package com.msa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 공통코드 도메인
 * @author fnfnksb@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CommonCode extends BaseModel {
    /**
     * 
     */
    private static final long serialVersionUID = 2004009091676222329L;

    private String codeGrp;
    private String code;
    private String codeNm;
    private String codeVal1;
    private String codeVal2;
    private String codeVal3;
    private String codeVal4;
    private String codeVal5;
    private String useYn;
    private int dispNo;
    private String regDt;
    private String modDt;
    private String regSeq;
    private String modSeq;
}
