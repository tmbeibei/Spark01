package com.atguigu.ct.producer;

import com.atguigu.ct.common.bean.Producer;
import com.atguigu.ct.producer.bean.LocalFileProducer;
import com.atguigu.ct.producer.io.LocalFileDataIn;
import com.atguigu.ct.producer.io.LocalFileDataOut;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes 启动对象
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {

        if(args == null || args.length <= 1){
            System.out.println("参数不正确");
            System.exit(1);
        }

        //1。创建生产者对象
        Producer producer = new LocalFileProducer();

        //2.设置输入输出路径
        producer.setIn(new LocalFileDataIn(args[0]));
        producer.setOut(new LocalFileDataOut(args[1]));

        //3.生产数据
        producer.produce();

        //4.关闭资源
        producer.close();
    }
}
