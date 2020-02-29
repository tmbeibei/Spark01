package com.atguigu.ct.consumer.bean;

import com.atguigu.ct.common.api.Column;
import com.atguigu.ct.common.api.Rowkey;
import com.atguigu.ct.common.api.TableRef;

/**
 * @author tianmin
 * @date 2020/2/2 0002
 * @notes
 */
@TableRef("ct:calllog")
public class Calllog {
    @Rowkey
    private String rowkey;

    @Column(colFamily = "caller")
    private String call1;

    @Column(colFamily = "caller")
    private String call2;

    @Column(colFamily = "caller")
    private String calltime;

    @Column(colFamily = "caller")
    private String duration;

    @Column(colFamily = "caller")
    private String flg = "1";

    public Calllog() {
    }

    public Calllog(String data) {
        String[] fields = data.split("\t");
        call1 = fields[0];
        call2 = fields[1];
        calltime = fields[2];
        duration = fields[3];
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getCall1() {
        return call1;
    }

    public void setCall1(String call1) {
        this.call1 = call1;
    }

    public String getCall2() {
        return call2;
    }

    public void setCall2(String call2) {
        this.call2 = call2;
    }

    public String getCalltime() {
        return calltime;
    }

    public void setCalltime(String calltime) {
        this.calltime = calltime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
