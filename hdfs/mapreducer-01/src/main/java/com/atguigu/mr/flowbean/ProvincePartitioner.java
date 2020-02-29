package com.atguigu.mr.flowbean;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author tianmin
 * @date 2020/2/4 0004
 * @notes 自定义分区
 */
public class ProvincePartitioner extends Partitioner<Text,FlowBean> {
    public int getPartition(Text text, FlowBean flowBean, int i) {
        // 1 获取手机号前三位
        String prePhone = text.toString().substring(0, 3);

        int partition = 4;
        if("136".equals(prePhone)){
            partition = 0;
        }else if("137".equals(prePhone)){
            partition = 1;
        }else if("138".equals(prePhone)){
            partition = 2;
        }else if("139".equals(prePhone)){
            partition = 3;
        }
        return partition;
    }
}
