package com.atguigu.carbon;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author tianmin
 * @date 2020/2/12 0012
 * @notes
 */
public class CarbonReducer extends Reducer<User, Text,User,NullWritable> {

    private List<User> vList = new ArrayList<User>();

    @Override
    protected void reduce(User key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String date = "1970-01-01";
        for (Text value : values) {
            String[] words = value.toString().split("\t");
            String userId = words[0];
            String orderDate = words[1];
            int low = Integer.parseInt(words[2]);
            User user = new User(userId,orderDate,low);

            if("1970-01-01".equals(date)){
                vList.add(user);
                date = orderDate;
            }else {
                int day = daysBetween(date,orderDate);
                //日期连续
                if(day == 1){
                    vList.add(user);
                    date = orderDate;
                }else {
                    if(vList.size() >= 3){
                        writeContext(vList,context);
                         vList.clear();
                         vList.add(user);
                    }else{
                        vList.clear();
                        vList.add(user);
                    }
                }
            }
        }

        //最后也要判断写出、清空
        if(vList.size() >= 3){
            writeContext(vList, context);
        }
        vList.clear();
    }

    private void writeContext(List<User> vList, Context context) throws IOException, InterruptedException {

        for (User user : vList) {
            context.write(user, NullWritable.get());
        }
    }

    private int daysBetween(String date, String orderDate) {
        Date preDate = DateUtil.parse(date, "yyyy-MM-dd");
        Date curDate = DateUtil.parse(orderDate, "yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(preDate);
        long preTimeInMillis = calendar.getTimeInMillis();

        calendar.setTime(curDate);
        long curTimeInMillis = calendar.getTimeInMillis();

        return (int) ((curTimeInMillis - preTimeInMillis) / (3600 * 24 * 1000));
    }
}
