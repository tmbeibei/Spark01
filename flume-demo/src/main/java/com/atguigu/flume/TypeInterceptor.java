package com.atguigu.flume;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tianmin
 * @date 2020/2/14 0014
 * @notes 拦截器：能转数字通过CZ 否则US
 */
public class TypeInterceptor implements Interceptor {

    private List<Event> eventList;

    public void initialize() {
        eventList = new ArrayList<Event>();
    }

    public Event intercept(Event event) {
        // 1 获取头信息
        Map<String, String> headers = event.getHeaders();

        // 2 获取body信息
        String body = new String(event.getBody());

        // 3 判断是否能转换成数字
        if(NumberUtil.isNumeric(body)){
            headers.put("state", "CZ");
        }else{
            headers.put("state", "US");
        }

        // 4 返回
        return event;
    }

    public List<Event> intercept(List<Event> events) {
        // 1 清空几个
        eventList.clear();

        // 2 添加事件
        for (Event event : events) {
            eventList.add(intercept(event));
        }

        // 3 返回
        return eventList;
    }

    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        public Interceptor build() {
            return new TypeInterceptor();
        }

        public void configure(Context context) {

        }
    }
}
