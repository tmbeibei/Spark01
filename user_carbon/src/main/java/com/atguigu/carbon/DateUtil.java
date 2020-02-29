package com.atguigu.carbon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tianmin
 * @date 2020/2/12 0012
 * @notes
 */
public class DateUtil {
    /**
     * 把日期转换成相应格式
     * @param stringDate
     * @param format
     * @return
     */
    public static Date parse(String stringDate,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
