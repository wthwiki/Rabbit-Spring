package cn.rabbit.springframework.beans.factory.core.io;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        if(location.startsWith(ResourceLoader.CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(ResourceLoader.CLASSPATH_URL_PREFIX.length()));
        }else{
            try {
                URL urlResource = new URL(location);
                return new UrlResource(urlResource);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
