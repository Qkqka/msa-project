package com.msa.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {
    private String prdId;
    private String prdName;
    private int price;
    private String typeCode;
    private String typeName;
}
