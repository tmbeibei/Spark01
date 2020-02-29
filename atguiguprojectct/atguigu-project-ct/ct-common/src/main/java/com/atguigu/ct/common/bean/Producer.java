package com.atguigu.ct.common.bean;

import java.io.Closeable;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes 生产者接口
 */
public interface Producer extends Closeable {
    /**
     * 数据来源
     * @param in
     */
    void  setIn(DataIn in);

    /**
     * 数据输出
     * @param out
     */
    void setOut(DataOut out);

    /**
     * 生产数据
     */
    void produce();
}
