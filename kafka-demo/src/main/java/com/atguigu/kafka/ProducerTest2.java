package com.atguigu.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author tianmin
 * @date 2020/2/15 0015
 * @notes
 */
public class ProducerTest2 {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        //InputStream resourceAsStream = ProducerTest2.class.getClassLoader().getResourceAsStream("producer.properties");
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("producer.properties");
        prop.load(resourceAsStream);

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>("bigdata", "tianmin-"+i));
        }

        producer.close();
    }
}
