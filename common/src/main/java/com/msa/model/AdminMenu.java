package com.msa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 관리자 메뉴 모델
 * @author fnfnksb@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminMenu extends BaseModel {

    private static final long serialVersionUID = -5131054354709613996L;

    /*
     * 관리자 메뉴 고유번호
     */
    private long adminMenuSeq;

    /*
     * 상위 관리자 메뉴 고유번호
     */
    private long prntAdminMenuSeq;

    /*
     * 메뉴명
     */
    private String menuNm;

    /*
     * 메뉴URL
     */
    private String menuUrl;

    /*
     * 메뉴 경로
     */
    private String menuPath;

    /*
     * 메뉴 레벨
     */
    private int menuLv;

    /*
     * 전시순서
     */
    private long dispNo;

    /*
     * 전시여부
     */
    private String dispYn;

    /*
     * 사용여부
     */
    private String useYn;
}
