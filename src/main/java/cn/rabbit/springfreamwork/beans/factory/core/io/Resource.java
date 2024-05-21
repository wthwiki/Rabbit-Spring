package cn.rabbit.springfreamwork.beans.factory.core.io;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {
    InputStream getInputStream() throws IOException;
}
