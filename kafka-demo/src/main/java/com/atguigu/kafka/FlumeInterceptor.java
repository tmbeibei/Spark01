package com.atguigu.kafka;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tianmin
 * @date 2020/2/15 0015
 * @notes
 */
public class FlumeInterceptor implements Interceptor {

    private List<Event> eventList;

    public void initialize() {
        eventList = new ArrayList<Event>();
    }

    public Event intercept(Event event) {
        // 1 获取头信息
        Map<String, String> headers = event.getHeaders();

        // 2 获取body
        String body = new String(event.getBody());

        // 3 添加头
        if(body.contains("hello")){
            headers.put("topic", "first");
        }else{
            headers.put("topic", "bigdata");
        }
        // 4 返回
        return event;
    }

    public List<Event> intercept(List<Event> events) {
        // 1 清空集合
        eventList.clear();

        // 2 循环添加
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
            return new FlumeInterceptor();
        }

        public void configure(Context context) {

        }
    }
}
