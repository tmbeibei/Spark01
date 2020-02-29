package com.atguigu.mr.flowsort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author tianmin
 * @date 2020/2/5 0005
 * @notes
 */
public class ProvincePartitions extends Partitioner<FlowBean, Text> {
    public int getPartition(FlowBean bean, Text text, int i) {
        String prePhone = text.toString().substring(0,3);

        int partitions = 4;

        if("136".equals(prePhone)){
            partitions = 0;
        }else if("137".equals(prePhone)){
            partitions = 1;
        }else if("138".equals(prePhone)){
            partitions = 2;
        }else if("139".equals(prePhone)){
            partitions = 3;
        }

        return partitions;
    }
}
