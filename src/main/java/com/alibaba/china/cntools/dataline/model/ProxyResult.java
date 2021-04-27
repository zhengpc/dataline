package com.alibaba.china.cntools.dataline.model;

import lombok.Data;

/**
 * @author zhengpengcheng
 * @date 2020/12/23
 */
@Data
public class ProxyResult<T> extends ResultModel<T> {

    /**
     * 外部唯一标识
     */
    private String outId;

}
