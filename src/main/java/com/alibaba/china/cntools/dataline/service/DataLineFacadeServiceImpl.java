package com.alibaba.china.cntools.dataline.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alibaba.china.cntools.dataline.dispather.ServiceDispatcher;
import com.alibaba.china.cntools.dataline.exception.DataLineException;
import com.alibaba.china.cntools.dataline.exception.ErrorCodeEnum;
import com.alibaba.china.cntools.dataline.exception.ExceptionUtils;
import com.alibaba.china.cntools.dataline.model.DataRequest;
import com.alibaba.china.cntools.dataline.model.DataResponse;
import com.alibaba.china.cntools.dataline.model.ProxyParam;
import com.alibaba.china.cntools.dataline.model.ProxyResult;
import com.alibaba.fastjson.JSON;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhengpengcheng
 * @date 2020/12/23
 */
public class DataLineFacadeServiceImpl implements DataLineFacadeService {

    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger("dataline");

    @Override
    public DataResponse requestData(DataRequest dataRequest) {
        DataResponse response = new DataResponse();

        try {
            List<ProxyParam> paramList = Optional.ofNullable(dataRequest)
                .map(DataRequest::getParamList)
                .orElse(null);
            if (CollectionUtils.isEmpty(paramList)) {
                logger.warn("ILLEGAL_PARAMS,paramList is empty!");
                throw new DataLineException(ErrorCodeEnum.ILLEGAL_PARAMS);
            }

            List<ProxyResult> resultList = paramList.stream()
                .filter(Objects::nonNull)
                .map(param -> process(dataRequest.getCurrentUserId(), param))
                .collect(Collectors.toList());

            response.setData(resultList);
            response.setSuccess(true);
        } catch (Exception e) {
            logger.error("requestData execute failed!", e);
            response.setSuccess(false);
            ExceptionUtils.resolveException(response, e);
        }

        return response;
    }

    @Override
    public ProxyResult requestData(Long currentUserId, ProxyParam proxyParam) {
        return process(currentUserId, proxyParam);
    }

    @Override
    public ProxyResult requestData(Long currentUserId, String serviceId, Map<String, Object> params) {
        ProxyParam proxyParam = new ProxyParam();
        proxyParam.setCurrentUserId(currentUserId);
        proxyParam.setServiceId(serviceId);
        proxyParam.setParam(JSON.toJSONString(params));
        return process(currentUserId, proxyParam);
    }

    /**
     * @param currentUserId
     * @param proxyParam
     * @return
     */
    protected ProxyResult process(Long currentUserId, ProxyParam proxyParam) {
        if (currentUserId != null && proxyParam != null && proxyParam.getCurrentUserId() == null) {
            proxyParam.setCurrentUserId(currentUserId);
        }
        return ServiceDispatcher.forward(proxyParam);
    }

}
