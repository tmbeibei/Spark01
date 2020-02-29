package com.atguigu.flume;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

import java.util.Random;

/**
 * @author tianmin
 * @date 2020/2/14 0014
 * @notes
 */
public class DiySource extends AbstractSource implements Configurable, PollableSource {

    private String preFix;
    private String subFix;
    private Random random;

    public void configure(Context context) {
        preFix = context.getString("preFix");
        subFix = context.getString("subFix","atguigu");
        random = new Random();
    }

    /**
     * 产生随机数，可以加上前后缀
     * @return
     * @throws EventDeliveryException
     */
    public Status process() throws EventDeliveryException {

        Status status = null;

        try {
            // 1 产生随机数
            int anInt = random.nextInt(100);

            // 2 创建事件
            SimpleEvent event = new SimpleEvent();

            // 3 给事件赋值
            event.setBody((preFix + "--" + anInt + "--" + subFix).getBytes());

            // 4 把事件写出
            getChannelProcessor().processEvent(event);
            status = Status.READY;
        } catch (Exception e) {
            status = Status.BACKOFF;
            e.printStackTrace();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 5 返回状态
        return status;
    }

    public long getBackOffSleepIncrement() {
        return 0;
    }

    public long getMaxBackOffSleepInterval() {
        return 0;
    }


}
