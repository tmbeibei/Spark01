package com.atguigu.mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/3 0003
 * @notes
 */
public class WordCountReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    private IntWritable v = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        // 1 计算value总和
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }

        // 2 写出
        v.set(sum);
        context.write(key, v);
    }
}
