package com.atguigu.mr.index;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.File;
import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/9 0009
 * @notes
 */
public class OneIndexMapper extends Mapper<LongWritable, Text,Text,LongWritable> {

    private String fileName;
    private Text k = new Text();
    private LongWritable v = new LongWritable(1);

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 1 获取文件名
        FileSplit split = (FileSplit) context.getInputSplit();
        fileName = split.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //atguigu pingping
        // 1 切割
        String[] fields = value.toString().split(" ");

        // 2 封装Key、Value
        for (String field : fields) {
            k.set(field + "__" + fileName);
            // 3 写出
            context.write(k, v);
        }
    }
}
