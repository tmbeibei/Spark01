package com.atguigu.mr.kv;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/4 0004
 * @notes
 */
public class KvTextMapper extends Mapper<Text,Text,Text, IntWritable> {

    private IntWritable v = new IntWritable(1);

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        //banzhang ni hao
        //写出
        context.write(key, v);
    }
}
