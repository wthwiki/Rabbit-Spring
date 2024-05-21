package cn.rabbit.springfreamwork.beans.factory.support;

import cn.rabbit.springfreamwork.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

     BeanDefinition getBeanDefinition(String beanName);
}
