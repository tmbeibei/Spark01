package com.atguigu.mr.cache;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/7 0007
 * @notes
 */
public class OrderBean implements Writable {
    private String id;
    private String pname;
    private Integer amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return id + "\t" + pname + "\t" + amount;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pname);
        out.writeInt(amount);
    }

    public void readFields(DataInput in) throws IOException {
        id = in.readUTF();
        pname = in.readUTF();
        amount = in.readInt();
    }
}
