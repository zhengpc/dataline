package com.alibaba.china.cntools.dataline.model;

import lombok.Data;

/**
 * @author zhengpengcheng
 * @date 2020/12/23
 */
@Data
public final class ProxyParam {

    /**
     * 当前操作用户ID
     */
    private Long currentUserId;

    /**
     * 外部唯一标识
     */
    private String outId;

    /**
     * 服务唯一标识
     */
    private String serviceId;

    /**
     * 参数
     */
    private String param;

}
