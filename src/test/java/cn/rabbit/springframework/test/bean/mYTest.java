package cn.rabbit.springframework.test.bean;

import cn.rabbit.springframework.aop.AdvisedSupport;
import cn.rabbit.springframework.aop.TargetSource;
import cn.rabbit.springframework.aop.aspectj.AspectJExpressionPointcut;
import cn.rabbit.springframework.aop.framework.JdkDynamicAopProxy;
import cn.rabbit.springframework.beans.factory.ClassPathXmlApplicationContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;

public class mYTest {

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
//        applicationContext.getBean();
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }

    @Test
    public void test_jdkDynamicAopProxy() {
        UserService userService = new UserService();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* cn.rabbit.springfreamwork.test.bean.IUserService.*(..))"));

        IUserService proxy = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.sayHello();
    }

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IUserService userService = (IUserService) applicationContext.getBean("userService");
        userService.sayHello();
    }
}

class UserServiceInterceptor implements MethodInterceptor{

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("拦截前");
            Object proceed = invocation.proceed();
            System.out.println("拦截后");
            return proceed;
        }

}