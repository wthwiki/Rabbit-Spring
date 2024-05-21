package cn.rabbit.springfreamwork.test.bean;

import cn.rabbit.springfreamwork.beans.factory.ClassPathXmlApplicationContext;
import cn.rabbit.springfreamwork.beans.factory.support.DefaultListableBeanFactory;
import cn.rabbit.springfreamwork.beans.factory.xml.XmlBeanDefinitionReader;

public class XmlReader {
    public static void main(String[] args) {
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
//        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
//        UserService userService =(UserService) beanFactory.getBean("userService");
//        System.out.println(userService);
//        userService.queryUserInfo();

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService);
        userService.queryUserInfo();
    }
}
