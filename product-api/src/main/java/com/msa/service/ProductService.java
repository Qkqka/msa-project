package com.msa.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.msa.client.CommonCodeFeignClient;
import com.msa.model.Code;
import com.msa.model.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CommonCodeFeignClient commonCodeFeignClient;

    public Product selectPrd(String prdId) {
        if (StringUtils.isBlank(prdId)) {
            throw new NullPointerException("상품아이디를 확인해주세요.");
        }
        Product prdInfo = Product.builder().prdId("prd1").prdName("상품1").price(1000).typeCode("type1").build();
        if (prdInfo != null) {
            Code typeInfo = this.commonCodeFeignClient.getCommonCode(prdInfo.getTypeCode());
            if (typeInfo != null) {
                prdInfo.setTypeName(typeInfo.getCodeName());
            }
        }
        return prdInfo;
    }
}
