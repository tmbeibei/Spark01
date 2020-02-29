package com.atguigu.carbon;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author tianmin
 * @date 2020/2/12 0012
 * @notes
 */
public class OrderByCompator extends WritableComparator {

    protected OrderByCompator(){
        super(User.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        User aUser = (User) a;
        User bUser = (User) b;


        return aUser.getUserId().compareTo(bUser.getUserId());
//        int result = aUser.getUserId().compareTo(bUser.getUserId());
//        if(result > 0){
//            result = 1;
//        }else if(result <0){
//            result = -1;
//        }
//        System.out.println("result:结果:" + result);
//        return result;
    }
}
