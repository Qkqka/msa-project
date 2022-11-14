package com.msa.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonCodeList implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3908868332005930105L;
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

    private int totalPage; // 총 페이지 수
    private int currentPage; // 현재 페이지
    private int rowCount; // 페이지 당 출력 데이터 개수
    private int totalCount; // 총 데이터 개수

    public int getOffset() {
        return (this.currentPage - 1) * this.rowCount;
    }
}
