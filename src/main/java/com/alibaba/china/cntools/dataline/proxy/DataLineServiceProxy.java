package com.alibaba.china.cntools.dataline.proxy;

import com.alibaba.china.cntools.dataline.model.ProxyParam;

/**
 * @author zhengpengcheng
 * @date 2020/12/23
 */
public interface DataLineServiceProxy {

    /**
     * @param proxyParam
     * @return
     * @throws Exception
     */
    Object invoke(ProxyParam proxyParam) throws Exception;

}
