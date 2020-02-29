package com.atguigu.carbon;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/12 0012
 * @notes
 */
public class User implements WritableComparable<User> {
    private String userId;
    private String orderDate;
    private int low;

    public User() {
    }

    public User(String userId, String orderDate, int low) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.low = low;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    @Override
    public String toString() {
        return userId + "\t" + orderDate + "\t" + low ;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(userId);
        out.writeUTF(orderDate);
        out.writeInt(low);
    }

    public void readFields(DataInput in) throws IOException {
        userId = in.readUTF();
        orderDate = in.readUTF();
        low = in.readInt();
    }

    public int compareTo(User o) {

        int result = userId.compareTo(o.getUserId());
        int dateResult = orderDate.compareTo(o.orderDate);
        if(result > 0){
            result = 1;
        }else if(result < 0){
            result = -1;
        }else{
            if(dateResult > 0){
                result = 1;
            }else if(dateResult < 0){
                result = -1;
            }else{
                result = 0;
            }
        }
        return  result;

//        if(this.getUserId().equals(o.getUserId())){
//            return this.getOrderDate().compareTo(o.getOrderDate());
//        }else{
//            return this.getUserId().compareTo(this.getUserId());
//        }
    }
}
