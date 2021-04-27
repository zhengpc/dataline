package com.alibaba.china.cntools.dataline.spring;

import com.alibaba.china.cntools.dataline.plugin.DataLinePlugin;
import com.alibaba.china.cntools.dataline.plugin.PluginRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author zhengpengcheng
 * @date 2020/12/24
 */
public class DataLinePluginPostProcessor implements BeanPostProcessor {

    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger("dataline");

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        boolean isPlugin = bean.getClass().isAnnotationPresent(DataLinePlugin.class);
        if (isPlugin) {
            try {
                PluginRegistry.register(bean);
            } catch (Throwable t) {
                logger.error("DataLinePlugin register failed", t);
                throw new BeanCreationException(beanName, "DataLinePlugin register failed", t);
            }
        }
        return bean;
    }

}
