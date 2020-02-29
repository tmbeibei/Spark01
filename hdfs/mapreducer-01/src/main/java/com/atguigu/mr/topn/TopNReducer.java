package com.atguigu.mr.topn;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author tianmin
 * @date 2020/2/9 0009
 * @notes
 */
public class TopNReducer extends Reducer<TopBean, Text,TopBean,NullWritable> {

    private Integer sumCount = 0;
    @Override
    protected void reduce(TopBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        context.write(key, NullWritable.get());
        sumCount++;

        if(sumCount >= 10){
            return;
        }

    }
}
