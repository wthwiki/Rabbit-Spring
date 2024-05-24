package cn.rabbit.springframework.test;

import cn.rabbit.springframework.beans.factory.PropertyValue;
import cn.rabbit.springframework.beans.factory.PropertyValues;
import cn.rabbit.springframework.beans.factory.support.DefaultListableBeanFactory;

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
