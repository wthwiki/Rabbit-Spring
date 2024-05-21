package cn.rabbit.springfreamwork.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.rabbit.springfreamwork.beans.BeansException;
import cn.rabbit.springfreamwork.beans.factory.DisposableBean;
import cn.rabbit.springfreamwork.beans.factory.InitializingBean;
import cn.rabbit.springfreamwork.beans.factory.PropertyValue;
import cn.rabbit.springfreamwork.beans.factory.PropertyValues;
import cn.rabbit.springfreamwork.beans.factory.config.AutowireCapableBeanFactory;
import cn.rabbit.springfreamwork.beans.factory.config.BeanDefinition;
import cn.rabbit.springfreamwork.beans.factory.config.BeanPostProcessor;
import cn.rabbit.springfreamwork.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();


    @Override
    Object createBean(String name, BeanDefinition beanDefinition) {
        return createBean(name, beanDefinition, null);
    }

    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    @Override
    Object createBean(String name, BeanDefinition beanDefinition, Object[] args) {

        // 创建实例
        Object bean = createBeanInstance(beanDefinition,name,args);
        // 填充属性
        applyPropertyValues(name, bean, beanDefinition);
        // 初始化bean
        bean=initializeBean( name , bean,beanDefinition);

        // 注册实现了 DisposableBean 接口的 Bean 对象
        registerDisposableBeanIfNecessary(name, bean, beanDefinition);

        // 判断 SCOPE_SINGLETON、SCOPE_PROTOTYPE
        if (beanDefinition.isSingleton()) {
            addSingleton(name, bean);
        }

        return bean;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 非 Singleton 类型的 Bean 不执行销毁方法
        if (!beanDefinition.isSingleton()) return;

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    private void registerDisposableBean(String beanName, DisposableBeanAdapter disposableBeanAdapter) {
        // TODO
    }


    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        }catch (Exception e){
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 实现接口 InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 2. 配置信息 init-method {判断是为了避免二次执行销毁}
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = beanDefinition.getAClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    abstract BeanPostProcessor[] getBeanPostProcessors();

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    private Object createBeanInstance(BeanDefinition beanDefinition, String name, Object[] args) {
        Constructor<?> ctor = null;
        try{
            Class<?> aClass = beanDefinition.getAClass();
            Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
            for (Constructor<?> c : declaredConstructors) {
                if (args != null && c.getParameterTypes().length == args.length) {
                    ctor= c;
                    break;
                }
            }
        }catch (Exception e) {
            throw new BeansException("create bean error");
        }

        return instantiationStrategy.instantiate(beanDefinition, name, ctor, args);
    }

}
