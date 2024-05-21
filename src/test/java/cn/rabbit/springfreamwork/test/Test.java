package cn.rabbit.springfreamwork.test;

import cn.rabbit.springfreamwork.beans.factory.BeanFactory;
import cn.rabbit.springfreamwork.beans.factory.PropertyValue;
import cn.rabbit.springfreamwork.beans.factory.PropertyValues;
import cn.rabbit.springfreamwork.beans.factory.config.BeanDefinition;
import cn.rabbit.springfreamwork.beans.factory.support.DefaultListableBeanFactory;
import cn.rabbit.springfreamwork.test.bean.UserService;

public class Test {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","张三"));
//        beanFactory.registerBeanDefinition("userService",new BeanDefinition(UserService.class,propertyValues));
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUserInfo();
    }
}
