package com.atguigu.ct.consumer.bean;

import com.atguigu.ct.common.bean.Consumer;
import com.atguigu.ct.common.constant.Names;
import com.atguigu.ct.consumer.dao.HBaseDao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes 消费者对象
 */
public class CalllogConsumer implements Consumer {
    /**
     * 消费数据
     */
    public void consume() {

        try {
            //1.创建配置对象
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties"));

            //2.获取flume采集的数据
            KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);

            //3.关注主题
            consumer.subscribe(Arrays.asList(Names.TOPIC.getValue()));

            //3.1初始化hbasedao
            HBaseDao hBaseDao = new HBaseDao();
            hBaseDao.init();

            //4.消费数据
            while (true){
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("kafka:" + record.value());
                    hBaseDao.insertData(record.value());
                    //Calllog calllog = new Calllog(record.value());
                    //hBaseDao.insertData(calllog);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 关闭资源
     * @throws IOException
     */
    public void close() throws IOException {
        //hBaseDao.release();
    }
}
