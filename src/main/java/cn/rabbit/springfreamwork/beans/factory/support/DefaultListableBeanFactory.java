package cn.rabbit.springfreamwork.beans.factory.support;

import cn.hutool.core.collection.ComputeIter;
import cn.rabbit.springfreamwork.beans.factory.config.BeanDefinition;
import cn.rabbit.springfreamwork.beans.factory.config.BeanPostProcessor;
import cn.rabbit.springfreamwork.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry{

    Map<String , BeanDefinition> beanDefinitionMap = new HashMap<>();
    List<BeanPostProcessor> beanPostProcessorList =new ArrayList<>();

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
        beanDefinitionMap.forEach((k,v)->{
            if(type.isAssignableFrom(v.getAClass())){
                beanMap.put(k, (T) v);
            }
        });
        return beanMap;
    }


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessorList.add(beanPostProcessor);
    }

    @Override
    public void destroySingletons() {
        singletonObjects.forEach((k,v)->{
//            if()
        });
    }

    @Override
    public BeanPostProcessor[] getBeanPostProcessors() {
        Map<String, BeanPostProcessor> beanPostProcessorMap = getBeanOfType(BeanPostProcessor.class);
        return beanPostProcessorMap.values().toArray(new BeanPostProcessor[beanPostProcessorMap.size()]);
    }
}
