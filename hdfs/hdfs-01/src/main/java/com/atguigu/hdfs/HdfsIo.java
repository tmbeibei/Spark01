package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author tianmin
 * @date 2020/2/3 0003
 * @notes
 */
public class HdfsIo {
    /**
     * 上传文件到hdfs
     */
    @Test
    public void testPutFileToHdfs() throws URISyntaxException, IOException, InterruptedException {
        //1.获取FileSystem对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop101:9000"), conf, "atguigu");

        //2.获取输入流
        FileInputStream fis = new FileInputStream("e://xiaohua.txt");

        //3.获取输出流
        FSDataOutputStream fos = fs.create(new Path("/xiaohua.txt"));

        //4.流的对拷
        IOUtils.copyBytes(fis, fos, conf);

        //5.关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }


    /**
     * 从hdfs下载文件
     */
    @Test
    public void testGetFileToLocal() throws URISyntaxException, IOException, InterruptedException {
        //1.获取HDFS文件系统对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop101:9000"), conf, "atguigu");

        //2.获取输入流
        FSDataInputStream fis = fs.open(new Path("/xiaohua.txt"));

        //3.获取输出流
        FileOutputStream fos = new FileOutputStream("e:/abc.txt");

        //4.流的对拷
        IOUtils.copyBytes(fis, fos, conf);

        //5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    // 下载第一块128M
    @Test
    public void testReadSeek1() throws URISyntaxException, IOException, InterruptedException {
        //1 获取HDFS文件系统
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop101:9000"), conf, "atguigu");

        //2 获取输入流
        FSDataInputStream fis = fs.open(new Path("/hadoop-2.7.2.tar.gz"));

        //3 获取输出流
        FileOutputStream fos = new FileOutputStream("e://hadoop-2.7.2.tar.gz.part1");

        //4 读取128M
        byte[] buf = new byte[1024];
        for (int i = 0; i < 1024 * 128; i++) {
            int len = fis.read(buf);
            fos.write(buf,0,len);
        }

        //5.关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    // 下载第二块
    @Test
    public void testReadSeek2()throws URISyntaxException, IOException, InterruptedException{
        // 1 获取HDFS文件系统
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop101:9000"), conf, "atguigu");

        // 2 获取输入流
        FSDataInputStream fis = fs.open(new Path("/hadoop-2.7.2.tar.gz"));

        // 3 指定到128M
        fis.seek(1024 * 1024 * 128);

        // 4 获取输出流
        FileOutputStream fos = new FileOutputStream("e://hadoop-2.7.2.tar.gz.part2");

        // 5 流的对拷
        IOUtils.copyBytes(fis, fos, conf);

        // 6 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }
}
