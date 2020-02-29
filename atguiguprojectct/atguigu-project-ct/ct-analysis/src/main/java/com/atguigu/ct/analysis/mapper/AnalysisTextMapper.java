package com.atguigu.ct.analysis.mapper;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/24 0024
 * @notes
 */
public class AnalysisTextMapper extends TableMapper<Text, Text> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        // 1 获取rowkey
        //5_19565082510_20200421112943_14410679238_0649_1
        String line = Bytes.toString(key.get());
        String[] words = line.split("_");
        String call1 = words[1];
        String call2 = words[3];
        String calltime = words[2];
        String duration = words[4];

        String year = calltime.substring(0, 4);
        String month = calltime.substring(0, 6);
        String date = calltime.substring(0, 8);
        //主叫年
        context.write(new Text(call1 + "_" + year), new Text(duration));
        //主叫月
        context.write(new Text(call1 + "_" + month), new Text(duration));
        //主叫日
        context.write(new Text(call1 + "_" + date), new Text(duration));

        //被叫年
        context.write(new Text(call2 + "_" + year), new Text(duration));
        //被叫月
        context.write(new Text(call2 + "_" + month), new Text(duration));
        //被叫日
        context.write(new Text(call2 + "_" + date), new Text(duration));
    }
}
