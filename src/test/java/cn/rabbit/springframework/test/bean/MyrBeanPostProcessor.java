package cn.rabbit.springframework.test.bean;

import cn.rabbit.springframework.beans.BeansException;
import cn.rabbit.springframework.beans.factory.config.BeanPostProcessor;

public class MyrBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("user".equals(beanName)){
            System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization");
        }
        UserService userService = (UserService) bean;
        return userService;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
