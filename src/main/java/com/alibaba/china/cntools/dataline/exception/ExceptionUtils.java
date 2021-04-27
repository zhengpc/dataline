package com.alibaba.china.cntools.dataline.exception;

import com.alibaba.china.cntools.dataline.model.ResultModel;

/**
 * @author zhengpengcheng
 * @date 2020/12/28
 */
public class ExceptionUtils {

    /**
     * @param resultModel
     * @param t
     */
    public static void resolveException(ResultModel resultModel, Throwable t) {
        if (t instanceof DataLineException) {
            resultModel.setErrorCode(((DataLineException)t).getErrorCode());
            resultModel.setErrorMessage(((DataLineException)t).getErrorMessage());
        } else {
            resultModel.setErrorCode(ErrorCodeEnum.UNKNOW_EXCEPTION.getErrorCode());
            resultModel.setErrorMessage(ErrorCodeEnum.UNKNOW_EXCEPTION.getErrorMessage());
        }
    }

}
