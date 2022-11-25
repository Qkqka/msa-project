package com.msa.exception;

import lombok.Getter;

/**
 * custom exception
 * @author fnfnksb@gmail.com
 */
@Getter
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 4248268824154626585L;

    private int resultCode;
    private String resultMsg;

    public CustomException(int resultCode, String resultMsg) {
        super(resultMsg);
        this.resultCode = resultCode;
    }
}
