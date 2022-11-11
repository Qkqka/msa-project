package com.msa.service;

import com.msa.client.CommonCodeFeignClient;
import com.msa.model.CommonCode;
import com.msa.model.Product;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

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
            CommonCode typeInfo = this.commonCodeFeignClient.getCommonCode(prdInfo.getTypeCode());
            if (typeInfo != null) {
                prdInfo.setTypeName(typeInfo.getCodeName());
            }
        }
        return prdInfo;
    }
}
