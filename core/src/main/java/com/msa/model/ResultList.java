package com.msa.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 공통응답모델 List
 * 
 * @author crewmate
 * @param <T>
 * @param <T>
 */
@Getter @Setter
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
//@RequiredArgsConstructor // final이나 @NonNull인 필드 값만 파라미터로 받는 생성자 만듦
public class ResultList<T> {

    private int resultCode;

    private List<T> data;

    private String resultMsg;

    public ResultList(int resultCode) {
        this.resultCode = resultCode;
    }

    public ResultList(List<T> data) {
        this.data = data;
    }

    public ResultList(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public ResultList(int resultCode, List<T> data) {
        this.resultCode = resultCode;
        this.data = data;
    }

    public ResultList(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ResultList(String resultMsg, List<T> data) {
        this.resultMsg = resultMsg;
        this.data = data;
    }
}
