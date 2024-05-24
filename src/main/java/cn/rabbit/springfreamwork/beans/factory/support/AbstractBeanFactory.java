package cn.rabbit.springfreamwork.beans.factory.support;

import cn.rabbit.springfreamwork.beans.BeansException;
import cn.rabbit.springfreamwork.beans.factory.BeanFactory;
import cn.rabbit.springfreamwork.beans.factory.FactoryBean;
import cn.rabbit.springfreamwork.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport  implements BeanFactory , BeanDefinitionRegistry{
    @Override
    public Object getBean(String name) {
        return doGetBean(name,  null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args);


    protected <T> T doGetBean(final String name, final Object[] args) {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }

}
