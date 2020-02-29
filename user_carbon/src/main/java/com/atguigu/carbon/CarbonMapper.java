package com.atguigu.carbon;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/12 0012
 * @notes
 */
public class CarbonMapper extends Mapper<LongWritable, Text,User, Text> {
    private User k = new User();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1 切割
        String[] words = value.toString().split("\t");
        String userId = words[0];
        String orderDate = words[1];
        int low = Integer.parseInt(words[2]);

        // 2 封装Key、Value
        k.setUserId(userId);
        k.setOrderDate(orderDate);

        // 3 写出
        context.write(k, value);
    }
}
