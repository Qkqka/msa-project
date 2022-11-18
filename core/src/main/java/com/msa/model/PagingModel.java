package com.msa.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 페이징 기본 도메인
 * @author fnfnksb@gmail.com
 */
@Getter @Setter
public class PagingModel extends BaseModel {

    /**
     * 
     */
    private static final long serialVersionUID = 3396797246534980770L;

    private int totalCount; // 총 데이터 개수
    private int currentPage = 1; // 현재 페이지
    private int totalPage; // 총 페이지 수
    private int rowCount = 30; // 페이지 당 데이터 개수
    private int startPage = this.getListSize();
    private boolean isPaging = true; // 페이징 여부

    public int getListSize() {
        return (this.currentPage - 1) * this.rowCount;
    }
}
