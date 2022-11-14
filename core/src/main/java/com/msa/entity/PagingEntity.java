package com.msa.entity;

import lombok.Data;

@Data
public class PagingEntity {

    private int totalPage; // 총 페이지 수
    private int currentPage; // 현재 페이지
    private int rowCount; // 페이지 당 출력 데이터 개수
    private int totalCount; // 총 데이터 개수
    private int offset;

}
