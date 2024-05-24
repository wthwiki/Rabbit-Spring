package cn.rabbit.springfreamwork;

import cn.rabbit.springfreamwork.aop.Advice;

public interface Advisor {
    Advice getAdvice();
}
