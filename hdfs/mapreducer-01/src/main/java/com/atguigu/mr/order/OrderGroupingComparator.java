package com.atguigu.mr.order;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author tianmin
 * @date 2020/2/5 0005
 * @notes
 */
public class OrderGroupingComparator extends WritableComparator {

    protected OrderGroupingComparator(){
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean aBean= (OrderBean) a;
        OrderBean bBean= (OrderBean) b;

        int result = aBean.getOrderId().compareTo(bBean.getOrderId());
        return  result;
    }
}
