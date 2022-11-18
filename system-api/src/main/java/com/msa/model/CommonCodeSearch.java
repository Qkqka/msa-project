package com.msa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 공통코드 검색 도메인
 * @author fnfnksb@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommonCodeSearch extends PagingModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1212256517769930590L;

    private String codeGrp;
    private String code;
    private String codeNm;
    private String useYn;
}
