package com.atguigu.mr.index;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/9 0009
 * @notes
 */
public class TwoIndexMapper extends Mapper<LongWritable, Text,Text,Text> {

    private Text k = new Text();
    private Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        atguigu__a.txt	3
//        atguigu__b.txt	2
//        atguigu__c.txt	2
        // 1 切割
        String[] words = value.toString().split("__");

        // 2 封装key、value
        k.set(words[0]);
        v.set(words[1].replace("\t", "-->"));

        // 3 写出
        context.write(k, v);
    }
}
