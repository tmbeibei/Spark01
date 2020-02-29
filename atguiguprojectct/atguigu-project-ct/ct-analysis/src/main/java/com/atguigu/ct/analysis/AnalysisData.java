package com.atguigu.ct.analysis;

import com.atguigu.ct.analysis.tool.AnalysisBeanTool;
import com.atguigu.ct.analysis.tool.AnalysisTextTool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author tianmin
 * @date 2020/2/3 0003
 * @notes
 */
public class AnalysisData {
    public static void main(String[] args) throws Exception {
        //int result = ToolRunner.run(new AnalysisTextTool(), args);
        int result = ToolRunner.run(new AnalysisBeanTool(), args);

    }
}
