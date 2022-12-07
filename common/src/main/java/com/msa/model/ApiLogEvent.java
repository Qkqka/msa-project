package com.msa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * api log model
 * 
 * @author fnfnksb@gmail.com
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApiLogEvent {

    /**
     * 이력 고유번호
     */
    private long logSeq;

    /**
     * 요청 방식 (GET/POST)
     */
    private String requestMethod;

    /**
     * API 요청 경로
     */
    private String requestUri;

    /**
     * API 성공여부
     */
    private String successYn;

    /**
     * 실행시간
     */
    private long execTime;

    /**
     * 요청 파라미터
     */
    private String requestParam;

    /**
     * 요청 본문
     */
    private String requestBody;

    /**
     * 응답 결과
     */
    private String responseResult;

    /**
     * 에러 메세지
     */
    private String errMsg;

    /**
     * 에러 로그
     */
    private String errLog;

    /**
     * 클라이언트 아이피
     */
    private String clientIp;

    /**
     * 사용자 에이전트
     */
    private String userAgent;

    /**
     * 등록일시
     */
    private String regDt;

    public ApiLogEvent(String requestMethod, String requestUri, String successYn, long execTime, String requestParam,
            String requestBody, String responseResult, String errMsg, String errLog, String clientIp,
            String userAgent) {
        this.requestMethod = requestMethod;
        this.requestUri = requestUri;
        this.successYn = successYn;
        this.execTime = execTime;
        this.requestParam = requestParam;
        this.requestBody = requestBody;
        this.responseResult = responseResult;
        this.errMsg = errMsg;
        this.errLog = errLog;
        this.clientIp = clientIp;
        this.userAgent = userAgent;
    }
}
