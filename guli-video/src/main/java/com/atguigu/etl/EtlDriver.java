package com.atguigu.etl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author tianmin
 * @date 2020/2/13 0013
 * @notes
 */
public class EtlDriver implements Tool {

    private Configuration configuration;

    public int run(String[] args) throws Exception {
        // 1 获取Job对象
        Job job = Job.getInstance(configuration);

        // 2 设置jar
        job.setJarByClass(EtlDriver.class);

        // 3 设置Map类型
        job.setMapperClass(EtlMapper.class);

        // 4 设置map输出的key、value类型
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);

        // 5 设置最终输出的key、value类型
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        // 6 设置输入、输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 7 提交job
        boolean result = job.waitForCompletion(true);

        return result ? 0 : 1;
    }

    public void setConf(Configuration conf) {
        configuration = conf;
    }

    public Configuration getConf() {
        return configuration;
    }

    public static void main(String[] args) {
        //args = new String[]{"D:\\大数据笔记\\hive\\2.资料\\04_data\\guiliVideo\\video\\2008\\0222","e:\\output1"};
        Configuration conf = new Configuration();
        try {
            int run = ToolRunner.run(conf, new EtlDriver(), args);
            System.exit(run);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
