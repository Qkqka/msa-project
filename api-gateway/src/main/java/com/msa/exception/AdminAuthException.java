package com.msa.exception;

import lombok.Getter;

@Getter
public class AdminAuthException extends RuntimeException {

    private static final long serialVersionUID = 7882091548504157801L;

    private int resultCode;
    private String resultMsg;

    public AdminAuthException(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
