package cn.rabbit.springframework.test.bean;

import cn.rabbit.springframework.beans.factory.ClassPathXmlApplicationContext;
import org.junit.Test;

public class AopTest {
    @Test
    public void test_aop(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = (IUserService) applicationContext.getBean("userService");
        userService.sayHello();
    }

}
