package cn.rabbit.springframework.aop.aspectj;

import cn.rabbit.springframework.aop.Advice;
import cn.rabbit.springframework.aop.Pointcut;
import cn.rabbit.springframework.aop.PointcutAdvisor;
import lombok.Data;

@Data
public class  AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;
    private Advice advice;
    private String expression;


    @Override
    public Pointcut getPointcut() {
        if(pointcut == null){
            return new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
