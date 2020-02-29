package com.atguigu.hive;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @author tianmin
 * @date 2020/2/12 0012
 * @notes
 */
public class MyUdf extends UDF {
    /**
     * 返回大写
     * @param word
     * @return
     */
    public String evaluate(final String word){
        if(word == null){
            return null;
        }

        return word.toUpperCase();
    }
}
