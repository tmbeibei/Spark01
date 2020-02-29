package com.atguigu.kafka;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tianmin
 * @date 2020/2/15 0015
 * @notes
 */
public class CounterInterceptor implements ProducerInterceptor<String,String> {

    private AtomicInteger success = new AtomicInteger(0);
    private AtomicInteger errors = new AtomicInteger(0);

    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if(metadata !=null){
            success.incrementAndGet();
        }else{
            errors.incrementAndGet();
        }
    }

    public void close() {
        System.out.println("success:" + success.get());
        System.out.println("fail:" + errors.get());
    }

    public void configure(Map<String, ?> configs) {

    }
}
