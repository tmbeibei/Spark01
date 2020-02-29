package com.atguigu.mr;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/17 0017
 * @notes
 */
public class FruitReducer extends TableReducer<LongWritable, Text, NullWritable> {
    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            // 1 切割
            String[] fields = value.toString().split("\t");

            // 2 封装Put对象
            Put put = new Put(Bytes.toBytes(fields[0]));
            byte[] family = Bytes.toBytes("info");
            put.addColumn(family, Bytes.toBytes("name"), Bytes.toBytes(fields[1]));
            put.addColumn(family, Bytes.toBytes("color"), Bytes.toBytes(fields[2]));

            // 3 写出
            context.write(NullWritable.get(), put);
        }
    }
}
