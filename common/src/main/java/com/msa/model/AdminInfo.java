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
public class AdminInfo extends CommonModel {

    private static final long serialVersionUID = 248695190893364620L;

    /**
     * 관리자 정보
     */
    private Admin admin;

    /**
     * 관리자 접근 가능 메뉴 목록
     */
    private List<AdminMenu> adminMenuList;
}
