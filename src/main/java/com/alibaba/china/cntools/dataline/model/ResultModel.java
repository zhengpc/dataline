package com.alibaba.china.cntools.dataline.model;

import lombok.Data;

/**
 * @author zhengpengcheng
 * @date 2020/12/25
 */
@Data
public class ResultModel<T> {

    /**
     * 成功与否
     */
    private boolean success;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 数据对象
     */
    private T data;

}
