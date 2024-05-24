package cn.rabbit.springframework.aop;

import cn.rabbit.springframework.Advisor;

public interface PointcutAdvisor extends Advisor {

        Pointcut getPointcut();
}
