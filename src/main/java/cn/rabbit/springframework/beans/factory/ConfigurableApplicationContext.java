package cn.rabbit.springframework.beans.factory;

import cn.rabbit.springframework.beans.BeansException;
import cn.rabbit.springframework.context.ApplicationEventPublisher;

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
