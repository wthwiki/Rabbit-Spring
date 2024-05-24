package cn.rabbit.springframework.beans.factory.config;

import cn.rabbit.springframework.beans.BeansException;

public interface BeanFactoryPostProcessor {
    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     */
    public  void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
