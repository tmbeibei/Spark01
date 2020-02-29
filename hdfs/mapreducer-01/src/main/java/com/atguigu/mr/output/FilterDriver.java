package com.atguigu.mr.output;

import com.atguigu.mr.wordcount.WordCountDriver;
import com.atguigu.mr.wordcount.WordCountMapper;
import com.atguigu.mr.wordcount.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/5 0005
 * @notes
 */
public class FilterDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1 获取Job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2 设置jar
        job.setJarByClass(FilterDriver.class);

        // 3 设置Map、Reducer
        job.setMapperClass(FilterMapper.class);
        job.setReducerClass(FilterReducer.class);

        // 4 设置Map输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        // 5 设置最终输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setOutputFormatClass(FileterFileOutput.class);

        args = new String[] {"e://input","e://output1"};

        // 6 设置输入、输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 7 提交Job
        boolean result = job.waitForCompletion(true);

        System.exit(result ?  0 : 1);
    }
}
