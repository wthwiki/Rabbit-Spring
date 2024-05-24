package cn.rabbit.springframework;

import cn.rabbit.springframework.aop.Advice;

public interface Advisor {
    Advice getAdvice();
}
