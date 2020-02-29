package com.atguigu.mr.index;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/9 0009
 * @notes
 */
public class TwoIndexReducer extends Reducer<Text,Text,Text,Text> {
    private Text v = new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
//        atguigu   a.txt-->3
//        atguigu   b.txt-->2
//        atguigu   c.txt-->2
        StringBuilder sb = new StringBuilder();
        for (Text value : values) {
            sb.append(value + "\t");
        }
        v.set(sb.toString());

        // 写出
        context.write(key, v);
    }
}
