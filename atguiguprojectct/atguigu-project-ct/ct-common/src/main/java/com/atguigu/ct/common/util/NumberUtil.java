package com.atguigu.ct.common.util;

import java.text.DecimalFormat;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes
 */
public class NumberUtil {
    /**
     * 把数字转出成指定长度字符串
     * @param num
     * @param len
     * @return
     */
    public static String format(int num,int len){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append("0");
        }

        DecimalFormat decimalFormat = new DecimalFormat(stringBuilder.toString());
        return decimalFormat.format(num);
    }
}
