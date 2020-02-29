package com.atguigu.mr.index;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/9 0009
 * @notes
 */
public class OneIndexReducer extends Reducer<Text, LongWritable,Text,LongWritable> {

    private LongWritable v = new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        //atguigu__a.txt 1
        // 1 合计
        long sum = 0;
        for (LongWritable value : values) {
            sum += value.get();
        }
        v.set(sum);

        // 2 写出
        context.write(key, v);
    }
}
