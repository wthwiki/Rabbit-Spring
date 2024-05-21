package cn.rabbit.springfreamwork.beans.factory.config;

public interface SingletonBeanRegistry {

    public Object getSingleton(String beanName);
}
