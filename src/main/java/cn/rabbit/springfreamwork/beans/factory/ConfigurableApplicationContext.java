package cn.rabbit.springfreamwork.beans.factory;

import cn.rabbit.springfreamwork.beans.BeansException;
import cn.rabbit.springfreamwork.context.ApplicationEvent;
import cn.rabbit.springfreamwork.context.ApplicationEventPublisher;

public interface ConfigurableApplicationContext extends ApplicationContext , ApplicationEventPublisher {

    void registerShutdownHook();

    void close();

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
