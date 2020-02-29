package com.atguigu.mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/3 0003
 * @notes
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    private Text k = new Text();
    private IntWritable v = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1 获取一行对象，并且拆分
        String[] words = value.toString().split(" ");

        // 2 循环写出
        for (String word : words) {
            k.set(word);

            context.write(k, v);
        }
    }
}
