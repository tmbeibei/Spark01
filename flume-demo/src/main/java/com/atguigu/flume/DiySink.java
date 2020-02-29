package com.atguigu.flume;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tianmin
 * @date 2020/2/14 0014
 * @notes
 */
public class DiySink extends AbstractSink implements Configurable {

    private String prefix;
    private String subfix;
    private Logger logger;


    public void configure(Context context) {
        logger = LoggerFactory.getLogger(DiySink.class);
        prefix = context.getString("prefix");
        subfix = context.getString("subfix");
    }

    public Status process() throws EventDeliveryException {
        // 1 定义状态
        Status status = null;

        // 2 获取Channel
        Channel channel = getChannel();

        // 3 获取事物
        Transaction transaction = channel.getTransaction();

        // 4 开启事物
        transaction.begin();

        try {
            // 5 获取Event
            Event event = channel.take();

            // 6 业务处理
            if(event != null){
                // 6.1 获取body
                String body = new String(event.getBody());
                // 6.2 封装数据,写出
                logger.info(prefix + body + subfix);
            }

            // 7 提交
            transaction.commit();
            status = Status.READY;
        } catch (ChannelException e) {
            e.printStackTrace();
            // 8 回滚
            transaction.rollback();
            status = Status.BACKOFF;
        } finally {
            // 9 关闭事物
            transaction.close();
        }

        // 10 返回状态
        return status;
    }


}
