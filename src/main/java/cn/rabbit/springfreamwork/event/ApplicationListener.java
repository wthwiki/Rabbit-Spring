package cn.rabbit.springfreamwork.event;

import cn.rabbit.springfreamwork.context.ApplicationEvent;

public abstract  class  ApplicationListener<T>{

    public void onApplicationEvent(T event) {

    }
}
