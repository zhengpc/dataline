package com.alibaba.china.cntools.dataline.model;

import java.util.List;

import lombok.Data;

/**
 * @author zhengpengcheng
 * @date 2020/12/23
 */
@Data
public class DataRequest {

    /**
     * 当前操作用户ID
     */
    private Long currentUserId;

    /**
     *
     */
    private List<ProxyParam> paramList;

}
