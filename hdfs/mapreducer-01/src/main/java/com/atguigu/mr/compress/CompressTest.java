package com.atguigu.mr.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;

/**
 * @author tianmin
 * @date 2020/2/7 0007
 * @notes
 */
public class CompressTest {
    public static void main(String[] args) throws Exception {
        //compressMeathod("E:/order.txt","org.apache.hadoop.io.compress.BZip2Codec");
        //compressMeathod("E:/order.txt","org.apache.hadoop.io.compress.GzipCodec");

        deCompress("e:/order.txt.bz2");
    }

    private static void deCompress(String filename) throws IOException {
        // 1 是否能解压缩
        CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());
        CompressionCodec codec = factory.getCodec(new Path(filename));
        if(codec == null){
            System.out.println("不能解压缩");
            return;
        }

        // 2 创建输入流
        CompressionInputStream cis = codec.createInputStream(new FileInputStream(filename));

        // 3 创建输出流
        FileOutputStream fos = new FileOutputStream(filename + ".decoded");

        // 4 流的对拷
        IOUtils.copyBytes(cis, fos, 1024*1024, false);

        // 5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(cis);
    }

    private static void compressMeathod(String filename, String compressCode) throws IOException, ClassNotFoundException {
        // 1 创建输入流
        FileInputStream fis = new FileInputStream(filename);

        // 2 创建输出流
        Class aClass = Class.forName(compressCode);
        CompressionCodec  codec = (CompressionCodec) ReflectionUtils.newInstance(aClass, new Configuration());
        FileOutputStream fos = new FileOutputStream(new File(filename + codec.getDefaultExtension()));
        CompressionOutputStream cos = codec.createOutputStream(fos);

        // 3 流的对拷
        IOUtils.copyBytes(fis, cos, 1024 * 1024, false);

        // 4 关闭资源
        IOUtils.closeStream(cos);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
    }
}
