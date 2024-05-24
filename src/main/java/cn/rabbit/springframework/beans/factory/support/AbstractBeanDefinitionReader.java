package cn.rabbit.springframework.beans.factory.support;

import cn.rabbit.springframework.beans.factory.core.io.DefaultResourceLoader;
import cn.rabbit.springframework.beans.factory.core.io.ResourceLoader;
import lombok.Data;

@Data
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

}
