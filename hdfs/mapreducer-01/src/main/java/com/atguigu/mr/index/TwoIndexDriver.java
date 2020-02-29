package com.atguigu.mr.index;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/9 0009
 * @notes
 */
public class TwoIndexDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1 获取Job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2 设置jar
        job.setJarByClass(TwoIndexDriver.class);

        // 3 设置Map、Reducer
        job.setMapperClass(TwoIndexMapper.class);
        job.setReducerClass(TwoIndexReducer.class);

        // 4 设置map输出阶段的Key、value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        // 5 设置最终输出的Key、Value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        args = new String[]{"E:\\output1","E:\\output2"};

        // 6 设置输入、输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 7 提交Job
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }
}
