package cn.rabbit.springfreamwork.beans.factory.config;


import cn.rabbit.springfreamwork.beans.factory.ListableBeanFactory;
import cn.rabbit.springfreamwork.event.SimpleApplicationEventMulticaster;

import java.util.Map;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory {
    void preInstantiateSingletons();

    <T> Map<String,T> getBeanOfType(Class<T> type);

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void registerSingleton(String name, Object bean);
}
