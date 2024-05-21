package cn.rabbit.springfreamwork.beans.factory.config;


import cn.rabbit.springfreamwork.beans.factory.ListableBeanFactory;

import java.util.Map;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory {
    void preInstantiateSingletons();

    <T> Map<String,T> getBeanOfType(Class<T> type);

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();
}
