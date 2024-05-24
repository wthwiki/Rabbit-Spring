package cn.rabbit.springframework.beans.factory;

import cn.rabbit.springframework.beans.BeansException;

public interface BeanFactory {

    public Object getBean(String name);
    Object getBean(String name, Object... args) throws BeansException;

}
