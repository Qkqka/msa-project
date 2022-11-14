package com.msa.mapper.entity;

import lombok.Data;

@Data
public class AdminEntity {

    /**
     * 관리자 고유번호
     */
    private String adminSeq;

    /**
     * 업체 고유번호
     */
    private String compSeq;

    /**
     * 관리자 ID
     */
    private String adminId;

    /**
     * 관리자 PW
     */
    private String adminPw;

    /**
     * 관리자 상태 코드
     */
    private String adminStatCd;

    /**
     * 관리자 유형 코드
     */
    private String adminTypeCd;
}
