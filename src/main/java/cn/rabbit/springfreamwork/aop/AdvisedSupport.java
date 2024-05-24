package cn.rabbit.springfreamwork.aop;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;


@Data
public class AdvisedSupport {

    @Setter
    @Getter
    private boolean proxyTargetClass = false;

    // 被代理的目标对象
    private TargetSource targetSource;
    // 方法拦截器
    private MethodInterceptor methodInterceptor;
    // 方法匹配器(检查目标方法是否符合通知条件)
    private MethodMatcher methodMatcher;

}
