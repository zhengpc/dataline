package com.alibaba.china.cntools.dataline.proxy;

import java.lang.reflect.Method;
import java.util.List;

import com.alibaba.china.cntools.dataline.model.ProxyParam;
import com.alibaba.fastjson.JSON;

/**
 * @author zhengpengcheng
 * @date 2021/01/04
 */
public class ProxyFactory {

    /**
     * @param target
     * @param method
     * @return
     */
    public static DataLineServiceProxy createServiceProxy(Object target, Method method) {
        Class[] paramTypes = method.getParameterTypes();
        switch (paramTypes.length) {
            case 0:
                return (proxyParam) -> method.invoke(target);
            case 1:
                if (ProxyParam.class.equals(paramTypes[0])) {
                    return (proxyParam) -> method.invoke(target, proxyParam);
                } else {
                    return (proxyParam) -> {
                        Object param = JSON.parseObject(proxyParam.getParam(), paramTypes[0]);
                        return method.invoke(target, param);
                    };
                }
            default:
                return (proxyParam) -> {
                    List<Object> params = JSON.parseArray(proxyParam.getParam(), paramTypes);
                    return method.invoke(target, params.toArray());
                };
        }
    }

}
