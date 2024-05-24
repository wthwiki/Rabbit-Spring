package cn.rabbit.springframework.beans.factory.config;

import cn.rabbit.springframework.beans.BeansException;

public interface AutowireCapableBeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
