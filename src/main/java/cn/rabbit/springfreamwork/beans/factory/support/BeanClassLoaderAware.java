package cn.rabbit.springfreamwork.beans.factory.support;

import cn.rabbit.springfreamwork.beans.factory.Aware;

public class BeanClassLoaderAware implements Aware {
    private ClassLoader classLoader;

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }
}
