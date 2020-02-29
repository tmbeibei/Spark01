package com.atguigu.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author tianmin
 * @date 2020/2/17 0017
 * @notes
 */
public class FruitDriver implements Tool {

    private Configuration configuration;

    public int run(String[] args) throws Exception {
        // 1 获取Job对象
        Job job = Job.getInstance(configuration);

        // 2 设置Jar
        job.setJarByClass(FruitDriver.class);

        // 3 设置Mapper、以及Key、Value输出类型
        job.setMapperClass(FruitMapper.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);

        // 4 设置Reducer、以及最终的输入、输出类型
        TableMapReduceUtil.initTableReducerJob(args[1],
                FruitReducer.class,
                job);

        // 5 设置输入、输出路径
        FileInputFormat.setInputPaths(job, args[0]);

        // 6 提交job
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
        //args = new String[]{"E:\\input\\a.txt","fruit1"};
        Configuration conf = new Configuration();
        try {
            int run = ToolRunner.run(conf, new FruitDriver(), args);

            System.exit(run);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
