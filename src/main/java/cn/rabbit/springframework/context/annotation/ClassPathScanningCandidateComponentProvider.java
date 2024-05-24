package cn.rabbit.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import cn.rabbit.springframework.beans.factory.config.BeanDefinition;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassPathScanningCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classSet = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classSet) {
            // 将扫描到的类封装成 BeanDefinition
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            candidates.add(beanDefinition);
        }
        return candidates;
    }
}
