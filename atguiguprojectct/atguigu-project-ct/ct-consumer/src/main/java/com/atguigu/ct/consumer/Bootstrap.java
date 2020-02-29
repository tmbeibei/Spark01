package com.atguigu.ct.consumer;

import com.atguigu.ct.common.bean.Consumer;
import com.atguigu.ct.consumer.bean.CalllogConsumer;

import java.io.IOException;

/**
 * 使用Kafka消费者获取flume采集的数据
 * 将数据存储到Hbase去
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes 启动消费者
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {
        //1.创建对象
        Consumer consumer = new CalllogConsumer();

        //2.消费数据
        consumer.consume();

        //3.关闭资源
        consumer.close();
    }
}
