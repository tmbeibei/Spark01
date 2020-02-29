package com.atguigu.mr.order;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/5 0005
 * @notes
 */
public class OrderBean implements WritableComparable<OrderBean> {
    private String OrderId;
    private Double price;

    public OrderBean() {
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return OrderId + "\t" + price;
    }

    public int compareTo(OrderBean o) {
        int result;
        int orderResult = this.OrderId.compareTo(o.getOrderId());
        if(orderResult == 0){
            if(this.price > o.getPrice()){
                result = -1;
            }else if(this.price < o.getPrice()){
                result = 1;
            }else{
                result = 0;
            }
        }else{
            result = orderResult;
        }
        return result;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(OrderId);
        out.writeDouble(price);
    }

    public void readFields(DataInput in) throws IOException {
        OrderId = in.readUTF();
        price = in.readDouble();
    }
}
