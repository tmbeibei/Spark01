package com.atguigu.mr.topn;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/9 0009
 * @notes
 */
public class TopBean implements WritableComparable<TopBean> {
    private String tel;
    private Integer upFlow;
    private Integer downFlow;
    private Integer sumFlow;

    public TopBean() {
    }

    public TopBean(String tel, Integer upFlow, Integer downFlow, Integer sumFlow) {
        this.tel = tel;
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = sumFlow;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Integer upFlow) {
        this.upFlow = upFlow;
    }

    public Integer getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Integer downFlow) {
        this.downFlow = downFlow;
    }

    public Integer getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Integer sumFlow) {
        this.sumFlow = sumFlow;
    }

    public int compareTo(TopBean o) {

        int result;
        if(this.sumFlow > o.getSumFlow()){
            result = -1;
        }else if(this.sumFlow < o.getSumFlow()){
            result = 1;
        }else{
            result = 0;
        }
        return result;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(tel);
        out.writeInt(upFlow);
        out.writeInt(downFlow);
        out.writeInt(sumFlow);
    }

    public void readFields(DataInput in) throws IOException {
        tel = in.readUTF();
        upFlow = in.readInt();
        downFlow = in.readInt();
        sumFlow = in.readInt();
    }

    @Override
    public String toString() {
        return tel + "\t" + upFlow + "\t" + downFlow + "\t" + sumFlow;
    }
}
