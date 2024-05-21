package cn.rabbit.springfreamwork.beans.factory.config;

import cn.rabbit.springfreamwork.beans.BeansException;

public interface AutowireCapableBeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
