package com.atguigu.ct.common.bean;

import java.io.Closeable;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes 消费者接口
 */
public interface Consumer extends Closeable {
    /**
     * 消费数据
     */
    void consume();
}
