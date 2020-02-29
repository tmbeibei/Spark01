package com.atguigu.ct.analysis.tool;

import com.atguigu.ct.analysis.io.MysqlBeanOutputFormat;
import com.atguigu.ct.analysis.io.MysqlTextOutputFormat;
import com.atguigu.ct.analysis.kv.AnalysisKey;
import com.atguigu.ct.analysis.kv.AnalysisValue;
import com.atguigu.ct.analysis.mapper.AnalysisBeanMapper;
import com.atguigu.ct.analysis.mapper.AnalysisTextMapper;
import com.atguigu.ct.analysis.reducer.AnalysisBeanReducer;
import com.atguigu.ct.analysis.reducer.AnalysisTextReducer;
import com.atguigu.ct.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

/**
 * @author tianmin
 * @date 2020/2/3 0003
 * @notes 分析数据的工具
 */
public class AnalysisBeanTool implements Tool {
    private Configuration configuration;

    public int run(String[] strings) throws Exception {
        // 1 获取Job
        Job job = Job.getInstance(configuration);
        job.setJarByClass(AnalysisBeanTool.class);

        // 1.1 只扫描caller列族
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes(Names.CF_CALLER.getValue()));
        // 2 设置Mapper
        TableMapReduceUtil.initTableMapperJob(
                Names.TABLE.getValue(),
                scan,
                AnalysisBeanMapper.class,
                AnalysisKey.class,
                Text.class,
                job
        );

        // 3 设置Reducer
        job.setReducerClass(AnalysisBeanReducer.class);

        // 4 设置最终输入、输出路径
        job.setOutputKeyClass(AnalysisKey.class);
        job.setOutputValueClass(AnalysisValue.class);

        // 5 设置outputformat
        job.setOutputFormatClass(MysqlBeanOutputFormat.class);

        // 6 提交Job
        boolean result = job.waitForCompletion(true);

        if (result) {
            return JobStatus.State.SUCCEEDED.getValue();
        } else {
            return JobStatus.State.FAILED.getValue();
        }
    }

    public void setConf(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConf() {
        return configuration;
    }
}
