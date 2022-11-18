package com.msa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.msa.model.Code;

@FeignClient("system-api")
public interface CommonCodeFeignClient {
    @GetMapping("/{codeId}")
    Code getCommonCode(@PathVariable("codeId") String codeId);
}
