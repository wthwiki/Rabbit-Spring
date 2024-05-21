package cn.rabbit.springfreamwork.beans.factory;

import cn.rabbit.springfreamwork.beans.BeansException;
import cn.rabbit.springfreamwork.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public interface BeanFactory {

    public Object getBean(String name);
    Object getBean(String name, Object... args) throws BeansException;

}
