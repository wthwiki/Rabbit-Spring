package cn.rabbit.springframework.beans.factory.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassPathResource :获取资源路径的一种实现方式，通过类路径进行获取
 */
public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassPathResource.class.getClassLoader()); ;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new IOException(path + " cannot be opened");
        }
        return is;
    }
}
