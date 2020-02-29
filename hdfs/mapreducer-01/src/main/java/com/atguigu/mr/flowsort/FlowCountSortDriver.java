package com.atguigu.mr.flowsort;

import com.atguigu.mr.flowbean.FlowDriver;
import com.atguigu.mr.flowbean.FlowMapper;
import com.atguigu.mr.flowbean.FlowReducer;
import com.atguigu.mr.flowbean.ProvincePartitioner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/4 0004
 * @notes
 */
public class FlowCountSortDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1 获取Job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2 设置jar
        job.setJarByClass(FlowCountSortDriver.class);

        // 3 设置Map、Reducer
        job.setMapperClass(FlowCountSortMapper.class);
        job.setReducerClass(FlowCountSortReducer.class);

        // 4 设置map输出阶段的Key、value类型
        job.setMapOutputKeyClass(com.atguigu.mr.flowsort.FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        // 5 设置最终输出的Key、Value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(com.atguigu.mr.flowsort.FlowBean.class);

        job.setPartitionerClass(ProvincePartitions.class);
        job.setNumReduceTasks(5);

        args = new String[]{"E:\\input","E:\\output1"};

        // 6 设置输入、输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 7 提交Job
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }
}
