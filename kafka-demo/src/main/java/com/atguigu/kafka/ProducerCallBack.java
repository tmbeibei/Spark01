package com.atguigu.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author tianmin
 * @date 2020/2/15 0015
 * @notes
 */
public class ProducerCallBack {
    public static void main(String[] args) {
        // 1 获取配置文件
        Properties props = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("producer.properties");
        try {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2 创建KafkaProducer对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        //3 发送消息
        for (int i = 0; i < 8; i++) {
            producer.send(new ProducerRecord<String, String>("bigdata", "min" + i), new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception == null){
                        System.out.println(metadata.partition() + "-----" + metadata.offset());
                    }else{
                        exception.printStackTrace();
                    }
                }
            });
        }

        // 4 关闭KafkaProducer对象
        producer.close();

    }
}
