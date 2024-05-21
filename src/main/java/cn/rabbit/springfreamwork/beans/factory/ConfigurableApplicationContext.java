package cn.rabbit.springfreamwork.beans.factory;

import cn.rabbit.springfreamwork.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{

    void registerShutdownHook();

    void close();

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
