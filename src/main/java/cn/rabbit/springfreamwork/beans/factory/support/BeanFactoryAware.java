package cn.rabbit.springfreamwork.beans.factory.support;

import cn.rabbit.springfreamwork.beans.BeansException;
import cn.rabbit.springfreamwork.beans.factory.Aware;
import cn.rabbit.springfreamwork.beans.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
