package com.atguigu.kafka;

import org.apache.kafka.clients.consumer.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author tianmin
 * @date 2020/2/15 0015
 * @notes
 */
public class ConsumerTest {
    public static void main(String[] args) {
        // 1 获取配置文件
        Properties props = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties");
        try {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2 创建消费者对象
        Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        // 3 订阅主题
        consumer.subscribe(Arrays.asList("thread"));

        //ConsumerConfig

        // 4 拉取数据
        while (true){
            ConsumerRecords<String, String> consumerRercords = consumer.poll(100);
            for (ConsumerRecord<String, String> rercord : consumerRercords) {
                System.out.println(rercord.key() + "--" + rercord.value());
            }
        }
    }
}
