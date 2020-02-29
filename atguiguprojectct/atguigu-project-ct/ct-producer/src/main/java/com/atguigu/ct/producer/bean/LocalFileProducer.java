package com.atguigu.ct.producer.bean;

import com.atguigu.ct.common.bean.DataIn;
import com.atguigu.ct.common.bean.DataOut;
import com.atguigu.ct.common.bean.Producer;
import com.atguigu.ct.common.util.DateUtil;
import com.atguigu.ct.common.util.NumberUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes 本地数据文件生产者
 */
public class LocalFileProducer implements Producer {
    private DataIn in;
    private DataOut out;
    private volatile boolean flg = true;

    /**
     * 设置输入路径
     * @param in
     */
    public void setIn(DataIn in) {
        this.in = in;
    }

    /**
     * 设置输出路径
     * @param out
     */
    public void setOut(DataOut out) {
        this.out = out;
    }

    /**
     * 生产数据
     */
    public void produce() {
        try {
            //1.读取数据
            List<Contact> contacts = in.read(Contact.class);

            while (flg){
                //2.随机查找2个电话号码
                int call1Index = new Random().nextInt(contacts.size());
                int call2Index;
                while (true){
                    call2Index = new Random().nextInt(contacts.size());
                    if(call1Index != call2Index){
                        break;
                    }
                }

                Contact contact1 = contacts.get(call1Index);
                Contact contact2 = contacts.get(call2Index);

                //3.随机生产通话时间
                String startDate = "20200101000000";
                String endDate = "20210101000000";

                long startTime = DateUtil.parse(startDate, "yyyyMMddHHmmss").getTime();
                long endTime = DateUtil.parse(endDate, "yyyyMMddHHmmss").getTime();

                long callTime = (long) (startTime + (endTime - startTime) * Math.random());

                String callTimeString = DateUtil.format(new Date(callTime), "yyyyMMddHHmmss");

                //4.随机产生通话时长
                String duration = NumberUtil.format((int) (Math.random() * 3000), 4);

                //5.生产通话记录
                Calllog calllog = new Calllog(contact1.getTel(), contact2.getTel(), callTimeString, duration);
                System.out.println(calllog);

                //6.写到输出文件中
                out.write(calllog);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void close() throws IOException {
        if(in != null){
            in.close();
        }

        if(out != null){
            out.close();
        }
    }
}
