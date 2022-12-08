package com.msa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 코드 도메인
 * @author fnfnksb@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Code extends BaseModel {

    private static final long serialVersionUID = 2004009091676222329L;

    /**
     * 코드 그룹
     */
    private String codeGrp;

    /**
     * 상세코드
     */
    private String code;

    /**
     * 코드명
     */
    private String codeNm;

    /**
     * 코드값1
     */
    private String codeVal1;

    /**
     * 코드값2
     */
    private String codeVal2;

    /**
     * 코드값3
     */
    private String codeVal3;

    /**
     * 코드값4
     */
    private String codeVal4;

    /**
     * 코드값5
     */
    private String codeVal5;

    /**
     * 사용여부
     */
    private String useYn;

    /**
     * 전시순서
     */
    private int dispNo;
}
