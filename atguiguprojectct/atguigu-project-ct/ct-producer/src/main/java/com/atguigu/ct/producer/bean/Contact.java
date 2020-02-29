package com.atguigu.ct.producer.bean;

import com.atguigu.ct.common.bean.Data;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes
 */
public class Contact extends Data {
    private String tel;
    private String name;


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(Object val) {
        String[] fields = val.toString().split("\t");
        tel = fields[0];
        name = fields[1];
    }

    @Override
    public String toString() {
        return "Contact{" +
                "tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
