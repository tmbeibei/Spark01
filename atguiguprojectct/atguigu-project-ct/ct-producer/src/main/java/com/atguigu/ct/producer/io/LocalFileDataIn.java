package com.atguigu.ct.producer.io;

import com.atguigu.ct.common.bean.Data;
import com.atguigu.ct.common.bean.DataIn;
import com.atguigu.ct.producer.bean.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes 本地数据文件输入
 */
public class LocalFileDataIn implements DataIn {
    private BufferedReader reader;

    public LocalFileDataIn(String path){
        setPath(path);
    }

    public void setPath(String path) {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object read() throws IOException {
        return null;
    }

    /**
     * 1.从数据文件中读取所有的数据
     * 2.将数据转换成指定类型的对象，封装为集合返回
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {
        String line;
        List<T> ts = new ArrayList<T>();

        try {
            while ((line = reader.readLine()) != null){
                T t = clazz.newInstance();
                t.setValue(line);
                ts.add(t);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return ts;
    }

    public void close() throws IOException {
        if(reader != null){
            reader.close();
        }
    }
}
