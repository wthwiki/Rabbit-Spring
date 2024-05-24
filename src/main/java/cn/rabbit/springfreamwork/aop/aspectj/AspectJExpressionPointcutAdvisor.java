package cn.rabbit.springfreamwork.aop.aspectj;

import cn.rabbit.springfreamwork.aop.Advice;
import cn.rabbit.springfreamwork.aop.Pointcut;
import cn.rabbit.springfreamwork.aop.PointcutAdvisor;
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
