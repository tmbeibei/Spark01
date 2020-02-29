package com.atguigu.mr3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author tianmin
 * @date 2020/2/17 0017
 * @notes
 */
public class Fruit3Driver implements Tool {

    private Configuration conf;

    public int run(String[] args) throws Exception {
        // 1 获取Job对象
        Job job = Job.getInstance(conf);

        // 2 设置Jar
        job.setJarByClass(Fruit3Driver.class);

        // 3 设置Map、以及Key、Value
        TableMapReduceUtil.initTableMapperJob(args[0],
                new Scan(),
                Fruit3Mapper.class,
                ImmutableBytesWritable.class,
                Put.class,
                job);

        // 4 设置Reducer 以及Key、Value
        TableMapReduceUtil.initTableReducerJob(args[1],
                Fruit3Rerducer.class,
                job);

        // 5 提交JOb
        boolean result = job.waitForCompletion(true);

        return result ? 0 : 1;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public Configuration getConf() {
        return conf;
    }

    public static void main(String[] args) {
        //args = new String[]{"fruit","fruit3"};
        Configuration conf = HBaseConfiguration.create();
        try {
            int run = ToolRunner.run(conf, new Fruit3Driver(), args);
            System.exit(run);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
