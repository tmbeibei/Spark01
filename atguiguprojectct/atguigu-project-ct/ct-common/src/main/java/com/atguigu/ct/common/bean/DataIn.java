package com.atguigu.ct.common.bean;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes
 */
public interface DataIn extends Closeable {
    /**
     * 路径设置
     * @param path
     */
    void setPath(String path);

    /**
     * 读取数据
     * @return
     * @throws IOException
     */
    Object read() throws IOException;

    /**
     * 读取数据集合
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    <T extends Data> List<T> read(Class<T> clazz) throws IOException;
}
