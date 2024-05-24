package cn.rabbit.springfreamwork.beans.factory.support;

import cn.hutool.core.collection.ComputeIter;
import cn.rabbit.springfreamwork.beans.factory.config.BeanDefinition;
import cn.rabbit.springfreamwork.beans.factory.config.BeanPostProcessor;
import cn.rabbit.springfreamwork.beans.factory.config.ConfigurableListableBeanFactory;
import cn.rabbit.springfreamwork.event.SimpleApplicationEventMulticaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry{

    Map<String , BeanDefinition> beanDefinitionMap = new HashMap<>();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    public void registerBeanDefinition(String name, BeanDefinition object){
        beanDefinitionMap.put(name,object);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }

    @Override
    public void preInstantiateSingletons() {

    }

    @Override
    public <T> Map<String,T> getBeanOfType(Class<T> type) {
        Map<String, T> beanMap=new HashMap<>();

        beanDefinitionMap.forEach((beanName,beanDefinition)->{
            if(type.isAssignableFrom(beanDefinition.getBeanClass())){
                beanMap.put(beanName, (T) getBean(beanName));
            }
        });
        return beanMap;
    }


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public void destroySingletons() {
        singletonObjects.forEach((k,v)->{
//            if()
        });
    }

    @Override
    public void registerSingleton(String name, Object bean) {
        singletonObjects.put(name,bean);
    }

    @Override
    public List<BeanPostProcessor> getBeanPostProcessors() {

        return beanPostProcessors;
    }
}
