package com.alibaba.china.cntools.dataline.exception;

import lombok.Data;

/**
 * @author zhengpengcheng
 * @date 2020/12/24
 */
@Data
public class DataLineException extends Exception {

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * @param errorCode
     * @param cause
     */
    public DataLineException(ErrorCodeEnum errorCode, Throwable cause) {
        super(cause);
        if (errorCode != null) {
            this.errorCode = errorCode.getErrorCode();
            this.errorMessage = errorCode.getErrorMessage();
        }
    }

    /**
     * @param errorCode
     */
    public DataLineException(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    /**
     * @param cause
     */
    public DataLineException(Throwable cause) {
        super(cause);
    }

    /**
     * @param errorCode
     * @param errorMessage
     */
    public DataLineException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * @param errorCode
     * @param errorMessage
     * @param cause
     */
    public DataLineException(String errorCode, String errorMessage, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
