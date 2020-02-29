package com.atguigu.mr.cache;

import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tianmin
 * @date 2020/2/7 0007
 * @notes
 */
public class DistributeMapper extends Mapper<LongWritable, Text,OrderBean, NullWritable> {

    private Map<String,String> pdMap = new HashMap<String, String>();
    private OrderBean k = new OrderBean();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
//        01	小米

        // 1 获取文件路径
        URI[] cacheFiles = context.getCacheFiles();
        String path = cacheFiles[0].getPath();

        // 2 创建输出流
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));

        // 3 封装Map
        String line = null;
        while ((line = reader.readLine()) != null){
            String[] fields = line.split("\t");
            pdMap.put(fields[0],fields[1]);
        }

        //4 关闭流
        IOUtils.closeStream(reader);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //        1001	01	1
        //        01	小米

        // 1 切割
        String[] fields = value.toString().split("\t");
        k.setId(fields[0]);
        k.setAmount(Integer.parseInt(fields[2]));

        String pname = pdMap.get(fields[1]);
        k.setPname(pname);

        // 2 写出
        context.write(k, NullWritable.get());
    }

}
