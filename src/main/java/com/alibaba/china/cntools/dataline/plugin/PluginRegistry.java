package com.alibaba.china.cntools.dataline.plugin;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import com.alibaba.china.cntools.dataline.exception.DataLineException;
import com.alibaba.china.cntools.dataline.exception.ErrorCodeEnum;
import com.alibaba.china.cntools.dataline.proxy.DataLine;
import com.alibaba.china.cntools.dataline.proxy.DataLineServiceProxy;
import com.alibaba.china.cntools.dataline.proxy.ProxyFactory;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.reflections.ReflectionUtils.withAnnotation;

/**
 * @author zhengpengcheng
 * @date 2020/12/23
 */
public class PluginRegistry {

    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger("dataline");

    /**
     *
     */
    private static final Map<String, DataLineServiceProxy> serviceProxyMap = Maps.newConcurrentMap();

    /**
     * @param plugin
     */
    public static void register(Object plugin) throws DataLineException {
        Set<Method> methods = ReflectionUtils.getMethods(plugin.getClass(), withAnnotation(DataLine.class));
        if (methods == null || methods.isEmpty()) {
            logger.warn("Not found method with annotation(DataLine.class),plugin={}", plugin.getClass());
            return;
        }

        for (Method method : methods) {
            if (method == null) {
                continue;
            }

            DataLine dataLine = method.getAnnotation(DataLine.class);
            String serviceId = dataLine.serviceId();
            if (StringUtils.isBlank(serviceId)) {
                logger.error("ServiceId is blank,plugin={}", plugin.getClass());
                continue;
            }

            if (serviceProxyMap.containsKey(serviceId)) {
                logger.error("ServiceId={} has already registered,plugin={}", serviceId, plugin.getClass());
                throw new DataLineException(ErrorCodeEnum.SERVICE_ALREADY_EXIST);
            }

            serviceProxyMap.put(serviceId, ProxyFactory.createServiceProxy(plugin, method));
        }
    }

    /**
     * @param serviceId
     * @return
     */
    public static DataLineServiceProxy getDataLineServiceProxy(String serviceId) {
        if (StringUtils.isBlank(serviceId)) {
            return null;
        }

        return serviceProxyMap.get(serviceId);
    }

}
