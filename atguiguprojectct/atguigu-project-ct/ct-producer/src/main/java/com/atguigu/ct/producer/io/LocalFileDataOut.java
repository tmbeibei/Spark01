package com.atguigu.ct.producer.io;

import com.atguigu.ct.common.bean.DataOut;

import java.io.*;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes
 */
public class LocalFileDataOut implements DataOut {
    private PrintWriter printWriter;

    public LocalFileDataOut(String path){
        setPath(path);
    }

    public void setPath(String path) {
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void write(Object value) throws IOException {
        write(value.toString());
    }

    /**
     * 写出
     * @param value
     * @throws IOException
     */
    public void write(String value) throws IOException {
        printWriter.println(value);
        printWriter.flush();
    }

    /**
     * 关闭资源
     * @throws IOException
     */
    public void close() throws IOException {
        if(printWriter != null){
            printWriter.close();
        }
    }
}
