package cn.rabbit.springframework.event;

import cn.rabbit.springframework.context.EventObject;

public abstract  class  ApplicationListener<T extends EventObject>{

    public void onApplicationEvent(T event) {

    }
}
