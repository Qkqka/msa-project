package com.msa.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 세션에 저장하는 관리자 정보 모델
 * @author fnfnksb@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminInfo extends BaseModel {

    private static final long serialVersionUID = 248695190893364620L;

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

    /**
     * 관리자 그룹 아이디
     */
    private String adminGrpIds;

    /**
     * 관리자 접근 가능 메뉴 목록
     */
    private List<AdminMenu> adminMenuList;
}
