package com.atguigu.mr.order;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/5 0005
 * @notes
 */
public class OrderReducer extends Reducer<OrderBean, NullWritable,OrderBean,NullWritable> {
    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        // 写出
        context.write(key, NullWritable.get());
    }
}
