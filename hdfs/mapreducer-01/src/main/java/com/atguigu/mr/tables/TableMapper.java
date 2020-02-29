package com.atguigu.mr.tables;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/7 0007
 * @notes
 */
public class TableMapper extends Mapper<LongWritable, Text,Text,TableBean> {

    private String fileName;
    private Text k = new Text();
    private TableBean v = new TableBean();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        fileName = fileSplit.getPath().getName();
        System.out.println("文件名:" + fileName);
        System.out.println("文件路径:" + fileSplit.getPath().toString());
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        1001	01	1
//        01	小米

        // 1 切割
        String[] fields = value.toString().split("\t");

        // 2 封装Key、Value
        //订单
        if(fileName.startsWith("order")){
            k.set(fields[1]);

            v.setId(fields[0]);
            v.setPid(fields[1]);
            v.setAmount(Integer.parseInt(fields[2]));
            v.setPname("");
            v.setFlag("order");
        }else{
            //产品
            k.set(fields[0]);

            v.setId("");
            v.setPid(fields[0]);
            v.setAmount(0);
            v.setPname(fields[1]);
            v.setFlag("pd");

        }

        // 3 写出
        context.write(k, v);
    }
}
