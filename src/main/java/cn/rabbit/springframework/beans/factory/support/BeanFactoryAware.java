package cn.rabbit.springframework.beans.factory.support;

import cn.rabbit.springframework.beans.BeansException;
import cn.rabbit.springframework.beans.factory.Aware;
import cn.rabbit.springframework.beans.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
