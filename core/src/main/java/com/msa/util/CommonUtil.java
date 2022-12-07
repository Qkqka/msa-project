package com.msa.util;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.msa.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtil {

    /**
     * 필수값 체크
     * 
     * 체크 유형 : String, List, Map
     * 
     * @param params 체크할 파라미터
     * @return
     */
    public static void checkParam(Object... params) {
        //log.info("params: {}", params);
        if (params == null || params.length == 0) {
            throw new NullPointerException("필수값이 존재하지 않습니다.");
        }

        for (Object obj : params) {
            if (obj instanceof String) {
                String str = (String) obj;
                if (StringUtils.isBlank(str)) {
                    throw new CustomException(30000, "필수값이 존재하지 않습니다.");
                }
            } else if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;
                if (CollectionUtils.isEmpty(list)) {
                    throw new CustomException(31000, "필수값이 존재하지 않습니다.");
                }
            } else if (obj instanceof Map<?, ?>) {
                Map<?, ?> map = (Map<?, ?>) obj;
                if (MapUtils.isEmpty(map)) {
                    throw new CustomException(32000, "필수값이 존재하지 않습니다.");
                }
            } else {
                if (Objects.isNull(obj)) {
                    throw new CustomException(33000, "필수값이 존재하지 않습니다.");
                }
            }
        }
    }
}
