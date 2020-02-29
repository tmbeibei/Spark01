package com.atguigu.mr.tables;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianmin
 * @date 2020/2/7 0007
 * @notes
 */
public class TableReducer extends Reducer<Text,TableBean,TableBean, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Context context) throws IOException, InterruptedException {
//        1001	01	1
//        1004	01	4
//        01	小米

        // 1 获取订单集合 商品表
        List<TableBean> tableBeans = new ArrayList<TableBean>();
        TableBean pdBean = new TableBean();
        for (TableBean value : values) {
            TableBean tmpBean = new TableBean();
            if("order".equals(value.getFlag())){
                try {
                    BeanUtils.copyProperties(tmpBean, value);
                    tableBeans.add(tmpBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    BeanUtils.copyProperties(pdBean, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }



        }

        // 2 封装订单表 并且写出
        for (TableBean tableBean : tableBeans) {
            tableBean.setPname(pdBean.getPname());
            context.write(tableBean, NullWritable.get());
        }

    }
}
