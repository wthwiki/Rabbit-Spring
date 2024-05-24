package cn.rabbit.springfreamwork.beans.factory.support;

import cn.rabbit.springfreamwork.beans.factory.config.SingletonBeanRegistry;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DefaultSingletonBeanRegistry  implements SingletonBeanRegistry {

    Map<String,Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

}
