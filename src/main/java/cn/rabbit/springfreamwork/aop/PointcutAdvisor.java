package cn.rabbit.springfreamwork.aop;

import cn.rabbit.springfreamwork.Advisor;

public interface PointcutAdvisor extends Advisor {

        Pointcut getPointcut();
}
