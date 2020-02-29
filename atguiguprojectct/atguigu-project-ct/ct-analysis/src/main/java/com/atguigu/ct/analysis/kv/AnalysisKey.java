package com.atguigu.ct.analysis.kv;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/25 0025
 * @notes
 */
public class AnalysisKey implements WritableComparable<AnalysisKey> {
    private String tel;
    private String callTime;

    public AnalysisKey() {
    }

    public AnalysisKey(String tel, String callTime) {
        this.tel = tel;
        this.callTime = callTime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    /**
     * 比较
     * @param that
     * @return
     */
    public int compareTo(AnalysisKey that) {
        int result = this.tel.compareTo(that.getTel());

        if(result == 0){
            result = this.callTime.compareTo(that.getCallTime());
        }

        return result;
    }

    /**
     * 序列化
     * @param out
     * @throws IOException
     */
    public void write(DataOutput out) throws IOException {
        out.writeUTF(tel);
        out.writeUTF(callTime);
    }

    /**
     * 反序列化
     * @param in
     * @throws IOException
     */
    public void readFields(DataInput in) throws IOException {
        tel = in.readUTF();
        callTime = in.readUTF();
    }
}
