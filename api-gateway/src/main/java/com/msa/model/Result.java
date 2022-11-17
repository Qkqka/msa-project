package com.msa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 공통응답모델
 * @author crewmate
 * @param <T>
 * @param <T>
 */
@Getter @Setter
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
//@RequiredArgsConstructor // final이나 @NonNull인 필드 값만 파라미터로 받는 생성자 만듦
public class Result<T> {

    private int resultCode;

    private T data;

    private String resultMsg;

    public Result(int resultCode) {
        this.resultCode = resultCode;
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Result(int resultCode, T data) {
        this.resultCode = resultCode;
        this.data = data;
    }

    public Result(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public Result(String resultMsg, T data) {
        this.resultMsg = resultMsg;
        this.data = data;
    }
}
