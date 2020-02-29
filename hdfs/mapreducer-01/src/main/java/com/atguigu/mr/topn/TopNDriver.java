package com.atguigu.mr.topn;

import com.atguigu.mr.index.OneIndexDriver;
import com.atguigu.mr.index.OneIndexMapper;
import com.atguigu.mr.index.OneIndexReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
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
public class TopNDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1 获取Job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2 设置jar
        job.setJarByClass(TopNDriver.class);

        // 3 设置Map、Reducer
        job.setMapperClass(TopNMapper.class);
        job.setReducerClass(TopNReducer.class);

        // 4 设置map输出阶段的Key、value类型
        job.setMapOutputKeyClass(TopBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        // 5 设置最终输出的Key、Value类型
        job.setOutputKeyClass(TopBean.class);
        job.setOutputValueClass(NullWritable.class);

        args = new String[]{"E:\\output1","E:\\output4"};

        // 6 设置输入、输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 7 提交Job
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }
}
