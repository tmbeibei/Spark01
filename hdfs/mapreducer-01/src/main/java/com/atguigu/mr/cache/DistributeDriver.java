package com.atguigu.mr.cache;

import com.atguigu.mr.tables.TableBean;
import com.atguigu.mr.tables.TableDriver;
import com.atguigu.mr.tables.TableMapper;
import com.atguigu.mr.tables.TableReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author tianmin
 * @date 2020/2/7 0007
 * @notes
 */
public class DistributeDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        // 1 获取Job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2 设置jar
        job.setJarByClass(DistributeDriver.class);

        // 3 设置Map、Reducer
        job.setMapperClass(DistributeMapper.class);

        // 4 设置最终输出的Key、Value类型
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);

        //job.setPartitionerClass(ProvincePartitioner.class);
        //job.setNumReduceTasks(5);

        job.addCacheFile(new URI("file:///e:/pd.txt"));
        job.setNumReduceTasks(0);

        args = new String[]{"E:\\input","E:\\output"};

        // 5 设置输入、输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 6 提交Job
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }
}
