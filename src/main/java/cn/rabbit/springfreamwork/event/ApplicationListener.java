package cn.rabbit.springfreamwork.event;

import cn.rabbit.springfreamwork.context.EventObject;

public abstract  class  ApplicationListener<T extends EventObject>{

    public void onApplicationEvent(T event) {

    }
}
