package com.atguigu.flume;

/**
 * @author tianmin
 * @date 2020/2/14 0014
 * @notes
 */
public class NumberUtil {
    public static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }

    public static void main(String[] args) {
        System.out.println(NumberUtil.isNumeric("5214"));
    }
}
