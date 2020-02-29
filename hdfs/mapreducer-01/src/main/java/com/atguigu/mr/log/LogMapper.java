package com.atguigu.mr.log;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/7 0007
 * @notes
 */
public class LogMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1 获取一行
        String line = value.toString();

        // 2 判断
        boolean result = parseLog(line,context);
        if(!result){
            return;
        }

        // 3 写出
        context.write(value, NullWritable.get());
    }

    /**
     * 数据清洗,小于11位的不要
     * @param line
     * @param context
     * @return
     */
    private boolean parseLog(String line, Context context) {
        String[] fields = line.split(" ");
        if(fields.length < 11){
            context.getCounter("map", "false").increment(1);
            return false;
        }

        context.getCounter("map", "true").increment(1);
        return true;
    }
}
