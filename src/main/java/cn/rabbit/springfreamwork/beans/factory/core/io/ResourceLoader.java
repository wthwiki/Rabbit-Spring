package cn.rabbit.springfreamwork.beans.factory.core.io;

public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 加载资源
     * @param location
     * @return
     */
    Resource getResource(String location);
}
