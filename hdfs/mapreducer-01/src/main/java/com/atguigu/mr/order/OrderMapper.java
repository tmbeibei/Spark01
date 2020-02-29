package com.atguigu.mr.order;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/5 0005
 * @notes
 */
public class OrderMapper extends Mapper<LongWritable, Text,OrderBean, NullWritable> {

    private OrderBean k = new OrderBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //0000001	Pdt_01	222.8

        // 1 切割
        String[] words = value.toString().split("\t");

        // 2 封装OrderBean
        String orderId = words[0];
        Double price = Double.parseDouble(words[2]);
        k.setOrderId(orderId);
        k.setPrice(price);

        // 3 写出
        context.write(k, NullWritable.get());
    }
}
