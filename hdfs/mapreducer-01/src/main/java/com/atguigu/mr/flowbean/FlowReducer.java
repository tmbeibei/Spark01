package com.atguigu.mr.flowbean;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/4 0004
 * @notes
 */
public class FlowReducer extends Reducer<Text,FlowBean,Text,FlowBean> {

    private FlowBean v = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
//   13568436656    2481	24681 20000
//   13568436656	1116	954   10000

        // 1 封装bean
        long upFlow = 0;
        long downFlow = 0;
        for (FlowBean value : values) {
            upFlow += value.getUpFlow();
            downFlow += value.getDownFlow();
        }
        v.setUpFlow(upFlow);
        v.setDownFlow(downFlow);
        v.setSumFlow(upFlow + downFlow);

        // 2 写出
        context.write(key, v);
    }
}
