package com.atguigu.ct.common.constant;

import com.atguigu.ct.common.bean.Val;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes
 */
public enum Names implements Val {
    NAMESPACE("ct"),
    TOPIC("ct"),
    TABLE("ct:calllog"),
    CF_CALLER("caller"),
    CF_CALLEE("callee"),
    CF_INFO("info"),
    JEDIS_USER("ct_user"),
    JEDIS_DATE("ct_date");

    private String name;
    private Names(String name){
        this.name = name;
    }


    public void setValue(Object val) {

    }

    public String getValue() {
        return name;
    }
}
