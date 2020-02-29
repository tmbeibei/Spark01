package com.atguigu.ct.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes
 */
public class DateUtil {
    /**
     * 把字符串转换成日期
     * @param dateString
     * @param format
     * @return
     */
    public static Date parse(String dateString,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 把日期转换成指定字符串格式
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
