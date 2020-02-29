package com.atguigu.mr.output;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/5 0005
 * @notes
 */
public class FRecordWriter extends RecordWriter<Text, NullWritable> {

    private FSDataOutputStream fosAtguigu;
    private FSDataOutputStream fosOther;

    public FRecordWriter(TaskAttemptContext context){
        try {
            // 1 获取FileSystem文件系统
            FileSystem fs = FileSystem.get(context.getConfiguration());

            // 2 获取atguigu输出流
            fosAtguigu = fs.create(new Path("e:\\atguigu.txt"));

            // 3 获取其他输出流
            fosOther = fs.create(new Path("e:\\other.log"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写出
     * @param text
     * @param nullWritable
     * @throws IOException
     * @throws InterruptedException
     */
    public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException {
        // 1 写出
        if(text.toString().contains("atguigu")){
            fosAtguigu.write(text.getBytes());
        }else{
            String data = text.toString() + "\n";
            fosOther.write(data.getBytes());
        }
    }

    /**
     * 关闭资源
     * @param taskAttemptContext
     * @throws IOException
     * @throws InterruptedException
     */
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        IOUtils.closeStream(fosAtguigu);
        IOUtils.closeStream(fosOther);
    }
}
