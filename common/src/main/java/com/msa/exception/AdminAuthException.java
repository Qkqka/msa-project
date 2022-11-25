package com.msa.exception;

import lombok.Getter;

/**
 * 관리자 인증 custom exception
 * @author fnfnksb@gmail.com
 */
@Getter
public class AdminAuthException extends RuntimeException {

    private static final long serialVersionUID = 7882091548504157801L;

    private int resultCode;
    private String resultMsg;

    public AdminAuthException(int resultCode, String resultMsg) {
        super(resultMsg);
        this.resultCode = resultCode;
    }
}
