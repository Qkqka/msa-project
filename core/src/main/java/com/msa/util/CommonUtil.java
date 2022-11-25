package com.msa.util;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtil {

    // 필수값체크
    @SafeVarargs
    public static <T> boolean checkParam(Object... params) {
        log.info("params: {}", params);
        if (params == null || params.length == 0) {
            throw new IllegalArgumentException("이건 필수값때문임");
        }

        List<Object> paramList = new ArrayList<>();
        for (Object obj : params) {
            paramList.add(obj);
        }

        return false;
    }
}
