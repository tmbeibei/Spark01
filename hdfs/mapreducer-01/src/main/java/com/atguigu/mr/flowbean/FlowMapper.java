package com.atguigu.mr.flowbean;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/4 0004
 * @notes
 */
public class FlowMapper extends Mapper<LongWritable, Text,Text,FlowBean> {

    private Text k = new Text();
    private FlowBean v = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

//        1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200

        // 1 获取一行，并且切割
        String[] words = value.toString().split("\t");

        // 2 封装flowbean对象
        k.set(words[1]);
        long upFlow = Long.parseLong(words[words.length - 3]);
        long downFlow = Long.parseLong(words[words.length - 2]);
        v.setUpFlow(upFlow);
        v.setDownFlow(downFlow);
        v.setSumFlow(upFlow + downFlow);

        // 3 写出
        context.write(k, v);
    }
}
