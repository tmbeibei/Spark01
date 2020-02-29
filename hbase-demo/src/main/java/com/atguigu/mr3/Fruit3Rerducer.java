package com.atguigu.mr3;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/17 0017
 * @notes
 */
public class Fruit3Rerducer extends TableReducer<ImmutableBytesWritable, Put, NullWritable> {
    @Override
    protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
        // 1 循环写出
        for (Put value : values) {

            context.write(NullWritable.get(), value);
        }
    }
}
