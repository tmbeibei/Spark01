package com.atguigu.kafka;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author tianmin
 * @date 2020/2/15 0015
 * @notes
 */
public class TimeIntecerptor implements ProducerInterceptor<String,String> {
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return new ProducerRecord<String, String>(record.topic(), record.partition(),
                record.key(), System.currentTimeMillis() +","+ record.value());
    }

    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    public void close() {

    }

    public void configure(Map<String, ?> configs) {

    }
}
