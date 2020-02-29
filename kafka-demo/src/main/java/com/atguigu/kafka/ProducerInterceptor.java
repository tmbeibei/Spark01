package com.atguigu.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author tianmin
 * @date 2020/2/15 0015
 * @notes
 */
public class ProducerInterceptor {
    public static void main(String[] args) {
        // 1 获取配置对象
        Properties props = new Properties();
        InputStream resourceAsStream = Thread.currentThread().
                getContextClassLoader().getResourceAsStream("producer.properties");
        try {
            props.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        List<String> interceptors = new ArrayList<String>();
//        interceptors.add("com.atguigu.kafka.TimeIntecerptor");
//        interceptors.add("com.atguigu.kafka.CounterInterceptor");
//        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        //ProducerConfig.INTERCEPTOR_CLASSES_CONFIG

        // 2 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        // 3 发送
        for (int i = 0; i < 10000000; i++) {
            producer.send(new ProducerRecord<String, String>("thread", "abc-"+i));
        }

        // 4 关闭
        producer.close();
    }
}
