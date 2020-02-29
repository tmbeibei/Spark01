package com.atguigu.mr2;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/17 0017
 * @notes
 */
public class Fruit2Mapper extends Mapper<LongWritable, Text, NullWritable, Put> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1 切割
        String[] fields = value.toString().split("\t");

        // 2 封装Put
        Put put = new Put(Bytes.toBytes(fields[0]));
        byte[] family = Bytes.toBytes("info");
        put.addColumn(family, Bytes.toBytes("name"), Bytes.toBytes(fields[1]));
        put.addColumn(family, Bytes.toBytes("color"), Bytes.toBytes(fields[2]));

        // 3 写出
        context.write(NullWritable.get(), put);
    }
}
