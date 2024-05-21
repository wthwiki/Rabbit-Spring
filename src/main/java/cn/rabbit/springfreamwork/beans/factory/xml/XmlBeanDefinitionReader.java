package cn.rabbit.springfreamwork.beans.factory.xml;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.rabbit.springfreamwork.beans.BeansException;
import cn.rabbit.springfreamwork.beans.factory.PropertyValue;
import cn.rabbit.springfreamwork.beans.factory.config.BeanDefinition;
import cn.rabbit.springfreamwork.beans.factory.core.io.Resource;
import cn.rabbit.springfreamwork.beans.factory.core.io.ResourceLoader;
import cn.rabbit.springfreamwork.beans.factory.support.AbstractBeanDefinitionReader;
import cn.rabbit.springfreamwork.beans.factory.support.BeanDefinitionRegistry;
import cn.rabbit.springfreamwork.beans.factory.config.BeanReference;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }


    @Override
    public void loadBeanDefinitions(Resource[] resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String[] configLocations) {
        for (String configLocation : configLocations) {
            loadBeanDefinitions(configLocation);
        }
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws IOException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (!(childNodes.item(i) instanceof Element)) continue;
            if(!"bean".equals(childNodes.item(i).getNodeName())) continue;

            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            //增加对init-method、destroy-method的读取
            String initMethod = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");
            String beanScope = bean.getAttribute("scope");

            System.out.println("id: " + id + ", name: " + name + ", className: " + className);

            Class<?> clazz = ClassUtil.loadClass(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            int length = bean.getChildNodes().getLength();
            for (int j = 0; j < length; j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                Element property = (Element) bean.getChildNodes().item(j);
                String nameAttribute = property.getAttribute("name");
                String valueAttribute = property.getAttribute("value");
                String ref = property.getAttribute("ref");

                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(ref) ? new BeanReference(ref) : valueAttribute;

                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(nameAttribute, value));
            }

            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            if(getRegistry().getBeanDefinition(beanName) != null){
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
