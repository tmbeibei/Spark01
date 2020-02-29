package com.atguigu.mr2;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/17 0017
 * @notes
 */
public class Fruit2Reducer extends TableReducer<NullWritable, Put,NullWritable> {
    @Override
    protected void reduce(NullWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
        for (Put value : values) {
            context.write(NullWritable.get(), value);
        }
    }
}
