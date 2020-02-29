package com.atguigu.ct.analysis.reducer;

import com.atguigu.ct.analysis.kv.AnalysisKey;
import com.atguigu.ct.analysis.kv.AnalysisValue;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/24 0024
 * @notes
 */
public class AnalysisBeanReducer extends Reducer<AnalysisKey,Text,AnalysisKey, AnalysisValue> {
    @Override
    protected void reduce(AnalysisKey key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //13333-20181010   10
        //13333-201810     10
        //13333-2018       10

        int sumCall = 0;
        int sumDuration = 0;
        for (Text value : values) {
            sumDuration += Double.parseDouble(value.toString());
            sumCall +=1;
        }
        context.write(key, new AnalysisValue(sumCall + "" , sumDuration + ""));
    }
}
