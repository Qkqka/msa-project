package com.msa.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class PagingModel implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int totalPage;

    private int currentPage;

    private int pagingCount;

}
