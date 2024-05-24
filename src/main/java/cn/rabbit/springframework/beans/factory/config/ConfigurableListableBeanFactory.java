package cn.rabbit.springframework.beans.factory.config;


import cn.rabbit.springframework.beans.factory.ListableBeanFactory;

import java.util.Map;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory {
    void preInstantiateSingletons();

    <T> Map<String,T> getBeanOfType(Class<T> type);

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void registerSingleton(String name, Object bean);
}
