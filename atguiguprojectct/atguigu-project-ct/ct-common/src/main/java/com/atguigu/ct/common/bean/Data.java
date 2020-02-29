package com.atguigu.ct.common.bean;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes
 */
public abstract class Data implements Val {
    private String content;


    public void setValue(Object val) {
        content = (String) val;
    }

    public Object getValue() {
        return content;
    }
}
