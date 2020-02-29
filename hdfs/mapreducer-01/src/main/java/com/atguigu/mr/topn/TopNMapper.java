package com.atguigu.mr.topn;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.*;

/**
 * @author tianmin
 * @date 2020/2/9 0009
 * @notes
 */
public class TopNMapper extends Mapper<LongWritable, Text,TopBean, NullWritable> {

    private Set<TopBean> telMap = new HashSet<TopBean>();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        13470253144	180	180	360
//        13509468723	7335	110349	117684
        // 1 切割
        String[] words = value.toString().split("\t");
        String tel = words[0];

        // 2 封装Key
        TopBean topBean = new TopBean(tel, Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]));

        // 3 写入集合
        telMap.add(topBean);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // 写出
        Iterator<TopBean> iterator = telMap.iterator();
        while (iterator.hasNext()){
            context.write(iterator.next(), NullWritable.get());
        }
    }
}
