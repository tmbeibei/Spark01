package com.atguigu.etl;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/13 0013
 * @notes
 */
public class EtlMapper extends Mapper<LongWritable, Text, NullWritable,Text> {

    private Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1 获取一行数据
        String line = value.toString();

        // 2 数据清洗
        String newLine = etlLine(line);
        if(newLine == null){
            context.getCounter("map", "false").increment(1);
            return;
        }
        context.getCounter("map", "true").increment(1);

        // 3 写出
        v.set(newLine);
        context.write(NullWritable.get(), v);
    }

    /**
     * 1.字段长度小于9
     * 2.去掉视频类别的空格
     * 3.相关视频ID由“\t”改为"&"
     * @param line
     * @return
     */
    private String etlLine(String line) {
        // 1 切割
        String[] fields = line.split("\t");

        // 2 字段长度小于9
        if(fields.length < 9){
            return null;
        }

        // 3 去掉视频类别的空格
        fields[3] = fields[3].replaceAll(" ", "");

        // 4 相关视频ID由“\t”改为"&"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if(i <= 8){
                if(i == fields.length - 1){
                    sb.append(fields[i]);
                }else{
                    sb.append(fields[i]).append("\t");
                }
            }else{
                if(i == fields.length - 1){
                    sb.append(fields[i]);
                }else{
                    sb.append(fields[i]).append("&");
                }
            }

        }
        // 5 返回
        return sb.toString();
    }


}
