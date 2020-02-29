package com.atguigu.ct.common.bean;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes
 */
public interface DataOut extends Closeable {
    /**
     * 路径设置
     * @param path
     */
    void setPath(String path);

    void write(Object value) throws IOException;

    void write(String value) throws IOException;
}
