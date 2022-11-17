package com.msa.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtil {

    // 필수값체크
    @SuppressWarnings("unchecked")
    public static <T> boolean checkParam(T... params) {
        log.info("params: {}", params);

        return false;
    }
}
