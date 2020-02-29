package com.atguigu.ct.analysis.reducer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/24 0024
 * @notes
 */
public class AnalysisTextReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int sumCall = 0;
        int sumDuration = 0;
        for (Text value : values) {
            sumDuration += Double.parseDouble(value.toString());
            sumCall +=1;
        }
        context.write(key, new Text(sumCall + "_" + sumDuration));
    }
}
