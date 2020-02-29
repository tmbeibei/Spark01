package com.atguigu.mr.kv;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/4 0004
 * @notes
 */
public class KvTextReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    private IntWritable v = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        // 1 求和
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }

        // 2 写出
        v.set(sum);
        context.write(key, v);
    }
}
