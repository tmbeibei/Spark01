package com.atguigu.ct.consumer.dao;

import com.atguigu.ct.common.bean.BaseDao;
import com.atguigu.ct.common.constant.Names;
import com.atguigu.ct.common.constant.ValueConstant;
import com.atguigu.ct.consumer.bean.Calllog;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes
 */
public class HBaseDao extends BaseDao {

    /**
     * 初始化数据
     */
    public void init() throws IOException {
        //开始
        getConnection();
        getAdmin();

        //创建命名空间和表
        createNamespaceNX(Names.NAMESPACE.getValue());
        createTableXX(Names.TABLE.getValue(),
                "com.atguigu.ct.consumer.coprocessor.InsertCalleeCoprocessor",
                ValueConstant.REGION_COUNT,
                Names.CF_CALLER.getValue(),Names.CF_CALLEE.getValue());


        //结束
        //close();
    }

    /**
     * 插入对象
     * @param calllog
     * @throws IOException
     */
    public void insertData(Calllog calllog) throws IOException {
        String rowkey = genRegionNum(calllog.getCall1(), calllog.getCalltime()) + "_"
                + calllog.getCall1()+ "_" + calllog.getCalltime()
                + "_"+ calllog.getCall2() + "_"+ calllog.getDuration();

        calllog.setRowkey(rowkey);
        putData(calllog);
    }

    /**
     * 插入数据
     * @param data
     */
    public void insertData(String data) throws IOException {
        //1.获取数据
        String[] fields = data.split("\t");
        String call1 = fields[0];
        String call2 = fields[1];
        String calltime = fields[2];
        String duration = fields[3];

        //2.生成rowkey = 分区号+主叫+通话时间+被叫+通话时长
        String rowkey = genRegionNum(call1, calltime) + "_" + call1+ "_"
                + calltime + "_"+ call2 + "_"+ duration + "_1";

        //3.生成put
        Put put = new Put(Bytes.toBytes(rowkey));
        byte[] family = Bytes.toBytes(Names.CF_CALLER.getValue());

        put.addColumn(family, Bytes.toBytes("call1"), Bytes.toBytes(call1));
        put.addColumn(family, Bytes.toBytes("call2"), Bytes.toBytes(call2));
        put.addColumn(family, Bytes.toBytes("calltime"), Bytes.toBytes(calltime));
        put.addColumn(family, Bytes.toBytes("duration"), Bytes.toBytes(duration));
        put.addColumn(family, Bytes.toBytes("flg"), Bytes.toBytes("1"));



        //被叫
//        String calleeRowkey = genRegionNum(call2, calltime) + "_" + call2+ "_"
//                + calltime + "_"+ call1 + "_"+ duration + "_0";
//        Put calleePut = new Put(Bytes.toBytes(calleeRowkey));
//        byte[] calleeFamily = Bytes.toBytes(Names.CF_CALLEE.getValue());
//
//        calleePut.addColumn(calleeFamily, Bytes.toBytes("call1"), Bytes.toBytes(call2));
//        calleePut.addColumn(calleeFamily, Bytes.toBytes("call2"), Bytes.toBytes(call1));
//        calleePut.addColumn(calleeFamily, Bytes.toBytes("calltime"), Bytes.toBytes(calltime));
//        calleePut.addColumn(calleeFamily, Bytes.toBytes("duration"), Bytes.toBytes(duration));
//        calleePut.addColumn(calleeFamily, Bytes.toBytes("flg"), Bytes.toBytes("0"));

        List<Put> puts = new ArrayList<Put>();
        puts.add(put);
        //puts.add(calleePut);

        //4.插入数据
        putData(Names.TABLE.getValue(), puts);

    }

    public void release() throws IOException {
        close();
    }
}
