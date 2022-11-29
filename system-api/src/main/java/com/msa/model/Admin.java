package com.msa.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 관리자 모델
 * @author fnfnksb@gmail.com
 */
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class Admin extends BaseModel {

    private static final long serialVersionUID = 9214170872674026619L;

    /**
     * 관리자 고유번호
     */
    private long adminSeq;

    /**
     * 업체 고유번호
     */
    private long compSeq;

    /**
     * 관리자 아이디
     */
    private String adminId;

    /**
     * 관리자 비밀번호
     */
    private String adminPw;

    /**
     * 관리자 상태코드
     */
    private String adminStatCd;

    /**
     * 관지라 유형코드
     */
    private String adminTypeCd;

    /**
     * 최종 로그인 일시
     */
    private String lastLoginDt;

    /**
     * 비밀번호 변경일시
     */
    private String pwChgDt;
}
