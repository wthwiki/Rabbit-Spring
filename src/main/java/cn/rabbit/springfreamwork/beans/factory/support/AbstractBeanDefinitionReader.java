package cn.rabbit.springfreamwork.beans.factory.support;

import cn.rabbit.springfreamwork.beans.BeansException;
import cn.rabbit.springfreamwork.beans.factory.core.io.DefaultResourceLoader;
import cn.rabbit.springfreamwork.beans.factory.core.io.Resource;
import cn.rabbit.springfreamwork.beans.factory.core.io.ResourceLoader;
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
