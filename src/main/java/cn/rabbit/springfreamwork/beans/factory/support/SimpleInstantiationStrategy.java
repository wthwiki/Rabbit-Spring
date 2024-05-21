package cn.rabbit.springfreamwork.beans.factory.support;

import cn.rabbit.springfreamwork.beans.BeansException;
import cn.rabbit.springfreamwork.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class aClass = beanDefinition.getAClass();
        try{
            if(ctor==null){
                return aClass.newInstance();
            }else{
                return ctor.newInstance(args);
            }
        }catch (Exception e){
            throw new BeansException("Failed to instantiate [" + aClass.getName() + "] {}"+ e);
        }
    }
}
