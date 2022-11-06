package com.msa.client;

import com.msa.domain.CommonCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("system-api")
public interface CommonCodeFeignClient {
    @GetMapping("/{codeId}")
    CommonCode getCommonCode(@PathVariable("codeId") String codeId);
}
