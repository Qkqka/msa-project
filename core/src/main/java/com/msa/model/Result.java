package com.msa.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 공통응답모델
 * 
 * @author fnfnksb@gmail.com
 */
@Data
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
//@RequiredArgsConstructor // final이나 @NonNull인 필드 값만 파라미터로 받는 생성자 만듦
@EqualsAndHashCode(callSuper = false)
public class Result extends CommonModel {

    private static final long serialVersionUID = -3277807226139433637L;

    private int resultCode = HttpStatus.OK.value();

    private Map<String, Object> resultData;

    private String resultMsg;

    public Result(int resultCode) {
        this.resultCode = resultCode;
    }

    public Result(Map<String, Object> resultData) {
        this.resultData = resultData;
    }

    public Result(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Result(int resultCode, Map<String, Object> resultData) {
        this.resultCode = resultCode;
        this.resultData = resultData;
    }

    public Result(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public Result(String resultMsg, Map<String, Object> resultData) {
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    public Result(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);

        this.resultData = map;
    }

    public Result(String resultMsg, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);

        this.resultMsg = resultMsg;
        this.resultData = map;
    }

    public Result(int errorCode, Exception e) {
        this.resultCode = errorCode;
        this.resultMsg = e.getMessage();
    }
}
