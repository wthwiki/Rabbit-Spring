package cn.rabbit.springfreamwork.beans.factory;

import cn.hutool.core.util.ObjectUtil;
import cn.rabbit.springfreamwork.beans.BeansException;
import cn.rabbit.springfreamwork.beans.factory.config.BeanFactoryPostProcessor;
import cn.rabbit.springfreamwork.beans.factory.config.BeanPostProcessor;
import cn.rabbit.springfreamwork.beans.factory.config.ConfigurableListableBeanFactory;
import cn.rabbit.springfreamwork.beans.factory.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory factory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = factory.getBeanOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            factory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory factory) {
        Map<String, BeanFactoryPostProcessor> factoryPostProcessorMap = factory.getBeanOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor factoryPostProcessor : factoryPostProcessorMap.values()) {
            factoryPostProcessor.postProcessBeanFactory(factory);
        }
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    abstract void refreshBeanFactory();


}
