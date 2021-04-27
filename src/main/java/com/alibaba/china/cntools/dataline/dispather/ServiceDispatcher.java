package com.alibaba.china.cntools.dataline.dispather;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import com.alibaba.china.cntools.dataline.exception.DataLineException;
import com.alibaba.china.cntools.dataline.exception.ErrorCodeEnum;
import com.alibaba.china.cntools.dataline.exception.ExceptionUtils;
import com.alibaba.china.cntools.dataline.model.ProxyParam;
import com.alibaba.china.cntools.dataline.model.ProxyResult;
import com.alibaba.china.cntools.dataline.proxy.DataLineServiceProxy;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.alibaba.china.cntools.dataline.plugin.PluginRegistry.getDataLineServiceProxy;

/**
 * 服务分发
 *
 * @author zhengpengcheng
 * @date 2020/12/27
 */
public class ServiceDispatcher {

    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger("dataline");

    /**
     * @param proxyParam
     * @return
     */
    public static ProxyResult forward(ProxyParam proxyParam) {
        ProxyResult proxyResult = new ProxyResult();
        try {
            try {
                String serviceId = Optional.ofNullable(proxyParam)
                    .map(ProxyParam::getServiceId)
                    .orElse(null);
                if (StringUtils.isBlank(serviceId)) {
                    logger.warn("ILLEGAL_PARAMS,serviceId is blank");
                    throw new DataLineException(ErrorCodeEnum.ILLEGAL_PARAMS);
                }

                DataLineServiceProxy serviceProxy = getDataLineServiceProxy(serviceId);
                if (serviceProxy == null) {
                    logger.warn("SERVICE_NOT_FOUND,serviceId={}", serviceId);
                    throw new DataLineException(ErrorCodeEnum.SERVICE_NOT_FOUND);
                }

                Object returnObj = serviceProxy.invoke(proxyParam);
                if (returnObj != null && returnObj instanceof ProxyResult) {
                    return proxyResult = (ProxyResult)returnObj;
                }

                proxyResult.setData(returnObj);
                proxyResult.setSuccess(true);
            } catch (Throwable t) {
                if (t instanceof InvocationTargetException) {
                    t = ((InvocationTargetException)t).getTargetException();
                }

                throw t;
            }
        } catch (Throwable e) {
            logger.error("service forward failed", e);
            proxyResult.setSuccess(false);
            ExceptionUtils.resolveException(proxyResult, e);
        } finally {
            proxyResult.setOutId(proxyParam.getOutId());
        }

        return proxyResult;
    }

}