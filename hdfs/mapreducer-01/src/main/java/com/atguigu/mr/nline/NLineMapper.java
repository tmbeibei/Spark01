package com.atguigu.mr.nline;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/4 0004
 * @notes
 */
public class NLineMapper extends Mapper<LongWritable, Text,Text,LongWritable> {

    private Text k = new Text();
    private LongWritable v = new LongWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 1 切割
        String[] words = value.toString().split(" ");

        // 2 循环写出
        for (String word : words) {
            k.set(word);

            context.write(k, v);
        }
    }
}
