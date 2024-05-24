package cn.rabbit.springframework.beans.factory.support;
import cn.rabbit.springframework.beans.BeansException;
import cn.rabbit.springframework.beans.factory.core.io.Resource;
import cn.rabbit.springframework.beans.factory.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource[] resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] configLocations);
}
