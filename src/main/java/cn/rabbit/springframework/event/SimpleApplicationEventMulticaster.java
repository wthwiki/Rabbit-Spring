package cn.rabbit.springframework.event;

import cn.rabbit.springframework.beans.factory.BeanFactory;
import cn.rabbit.springframework.context.ApplicationEvent;

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
