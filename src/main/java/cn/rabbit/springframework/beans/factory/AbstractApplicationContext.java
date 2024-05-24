package cn.rabbit.springframework.beans.factory;

import cn.rabbit.springframework.beans.BeansException;
import cn.rabbit.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.rabbit.springframework.beans.factory.config.BeanPostProcessor;
import cn.rabbit.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import cn.rabbit.springframework.beans.factory.core.io.DefaultResourceLoader;
import cn.rabbit.springframework.context.ApplicationEvent;
import cn.rabbit.springframework.context.ContextRefreshedEvent;
import cn.rabbit.springframework.event.ApplicationEventMulticaster;
import cn.rabbit.springframework.event.ApplicationListener;
import cn.rabbit.springframework.event.SimpleApplicationEventMulticaster;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    private ApplicationEventMulticaster applicationEventMulticaster;


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

        // 6. 初始化事件发布者
        initApplicationEventMulticaster();

        // 7. 注册事件监听器
        registerListeners();

        // 9. 发布容器刷新完成事件
        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void registerListeners() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        Map<String, ApplicationListener> listenerMap = beanFactory.getBeanOfType(ApplicationListener.class);
        listenerMap.
        forEach((beanName, listener) -> {
            applicationEventMulticaster.addApplicationListener(listener);
        });
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(ApplicationEventMulticaster.class.getName(), applicationEventMulticaster);
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
