package cn.rabbit.springfreamwork.event;

import cn.rabbit.springfreamwork.beans.factory.BeanFactory;
import cn.rabbit.springfreamwork.context.ApplicationEvent;
import cn.rabbit.springfreamwork.context.EventObject;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{


    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
