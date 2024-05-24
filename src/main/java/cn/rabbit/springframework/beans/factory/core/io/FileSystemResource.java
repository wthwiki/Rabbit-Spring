package cn.rabbit.springframework.beans.factory.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * FileSystemResource 通过文件进行资源获取
 */
public class FileSystemResource  implements Resource{
    private final String path;

    private File file;

    public FileSystemResource(String path, File file) {
        this.path = path;
        this.file = file;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(file.toPath());
    }
}
