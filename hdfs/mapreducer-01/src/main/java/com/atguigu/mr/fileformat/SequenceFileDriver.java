package com.atguigu.mr.fileformat;

import com.atguigu.mr.nline.NLineDriver;
import com.atguigu.mr.nline.NLineMapper;
import com.atguigu.mr.nline.NLineReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/4 0004
 * @notes
 */
public class SequenceFileDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"E:\\input","e:\\output1"};

        // 1 获取job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2 设置jar
        job.setJarByClass(SequenceFileDriver.class);

        // 3 设置Map、Reducer
        job.setMapperClass(SequenceFileMapper1.class);
        job.setReducerClass(SequneceFileReducer1.class);

        // 4 设置Map输出的Key、Value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);

        // 5 设置最终输出的Key、Value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);

        // 6 设置输入、输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 6.1 设置InputFormat实现类
        job.setInputFormatClass(WholeFileInputFormat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        // 7 提交Job
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }
}
