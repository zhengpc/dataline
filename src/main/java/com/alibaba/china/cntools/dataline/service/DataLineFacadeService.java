package com.alibaba.china.cntools.dataline.service;

import java.util.Map;

import com.alibaba.china.cntools.dataline.model.DataRequest;
import com.alibaba.china.cntools.dataline.model.DataResponse;
import com.alibaba.china.cntools.dataline.model.ProxyParam;
import com.alibaba.china.cntools.dataline.model.ProxyResult;

/**
 * @author zhengpengcheng
 * @date 2020/12/23
 */
public interface DataLineFacadeService {

    /**
     * @param dataRequest
     * @return
     */
    DataResponse requestData(DataRequest dataRequest);

    /**
     * @param currentUserId
     * @param proxyParam
     * @return
     */
    ProxyResult requestData(Long currentUserId, ProxyParam proxyParam);

    /**
     * @param currentUserId
     * @param serviceId
     * @param params
     * @return
     */
    ProxyResult requestData(Long currentUserId, String serviceId, Map<String, Object> params);

}
