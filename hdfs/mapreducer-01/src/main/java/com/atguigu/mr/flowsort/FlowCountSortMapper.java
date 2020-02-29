package com.atguigu.mr.flowsort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/4 0004
 * @notes
 */
public class FlowCountSortMapper extends Mapper<LongWritable, Text,FlowBean,Text> {

    private FlowBean k = new FlowBean();
    private Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //13470253144	180	180	360
        // 1 分割
        String[] words = value.toString().split("\t");
        String phone = words[0];
        long upFlow = Long.parseLong(words[1]);
        long downFlow = Long.parseLong(words[2]);
        long sumFlow = Long.parseLong(words[3]);

        k.setDownFlow(downFlow);
        k.setUpFlow(upFlow);
        k.setSumFlow(sumFlow);

        v.set(phone);

        // 2 写出
        context.write(k, v);
    }
}
