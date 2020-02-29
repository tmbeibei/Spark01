package com.atguigu.mr.nline;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/4 0004
 * @notes
 */
public class NLineReducer extends Reducer<Text,LongWritable,Text,LongWritable> {

    private LongWritable v = new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        long sum = 0;
        // 1 求和
        for (LongWritable value : values) {
            sum += value.get();
        }
        v.set(sum);

        // 2 写出
        context.write(key, v);
    }
}
