package com.alibaba.china.cntools.dataline.exception;

/**
 * @author zhengpengcheng
 * @date 2020/12/28
 */
public enum ErrorCodeEnum {

    SYSTEM_ERROR("SYSTEM_ERROR", "系统错误"),
    UNKNOW_EXCEPTION("UNKNOW_EXCEPTION", "出现了未知异常"),
    ILLEGAL_PARAMS("ILLEGAL_PARAMS", "请求参数不正确"),
    SERVICE_NOT_FOUND("SERVICE_NOT_FOUND", "服务未找到"),
    SERVICE_ALREADY_EXIST("SERVICE_ALREADY_EXIST", "服务已存在"),
    ;

    private final String errorCode;

    private final String errorMessage;

    /**
     * @param errorCode
     * @param errorMessage
     */
    ErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
