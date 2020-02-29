package com.atguigu.mr.fileformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/4 0004
 * @notes
 */
public class WholeRecordReader extends RecordReader<Text, BytesWritable> {

    private FileSplit fileSplit;
    private Text k = new Text();
    private BytesWritable v = new BytesWritable();
    private Configuration configuration;
    private boolean flg = true;

    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        this.fileSplit = (FileSplit) inputSplit;
        configuration = taskAttemptContext.getConfiguration();
    }

    public boolean nextKeyValue() throws IOException, InterruptedException {
        //SequenceFileOutputFormat
        if(flg){
            // 1 获取FileSystem文件系统
            Path path = fileSplit.getPath();
            FileSystem fs = path.getFileSystem(configuration);

            // 2 获取文件路径
            System.out.println("path:" + path);

            // 3 创建输出流对象
            FSDataInputStream fis = fs.open(path);

            // 4 封装v
            byte[] buf = new byte[(int) fileSplit.getLength()];
            IOUtils.readFully(fis, buf,0,buf.length);
            v.set(buf, 0, buf.length);

            // 5 封装k
            k.set(path.toString());

            // 6 关闭流
            IOUtils.closeStream(fis);

            flg = false;

            return  true;
        }

        return false;
    }

    public Text getCurrentKey() throws IOException, InterruptedException {
        return k;
    }

    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return v;
    }

    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    public void close() throws IOException {

    }
}
