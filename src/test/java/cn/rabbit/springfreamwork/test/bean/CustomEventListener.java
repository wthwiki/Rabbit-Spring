package cn.rabbit.springfreamwork.test.bean;

import cn.rabbit.springfreamwork.context.ApplicationEvent;
import cn.rabbit.springfreamwork.event.ApplicationListener;

import java.util.Date;

public class CustomEventListener extends ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {

        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }

}

