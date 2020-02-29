package com.atguigu.ct.common.bean;

import com.atguigu.ct.common.api.Column;
import com.atguigu.ct.common.api.Rowkey;
import com.atguigu.ct.common.api.TableRef;
import com.atguigu.ct.common.constant.Names;
import com.atguigu.ct.common.constant.ValueConstant;
import com.atguigu.ct.common.util.DateUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * @author tianmin
 * @date 2020/2/1 0001
 * @notes
 */
public abstract class BaseDao {
    private ThreadLocal<Connection> connHolder = new ThreadLocal<Connection>();
    private ThreadLocal<Admin> adminHolder = new ThreadLocal<Admin>();

    /**
     * 获取连接
     * @return
     * @throws IOException
     */
    protected synchronized Connection getConnection() throws IOException {
        Connection conn = connHolder.get();
        if(conn == null){
            Configuration conf = HBaseConfiguration.create();
            conn = ConnectionFactory.createConnection(conf);
            connHolder.set(conn);
        }
        return conn;
    }

    /**
     * 获取admin
     * @return
     * @throws IOException
     */
    protected synchronized Admin getAdmin() throws IOException{
        Admin admin = adminHolder.get();
        if(admin == null){
            Connection conn = getConnection();
            admin = conn.getAdmin();
            adminHolder.set(admin);
        }
        return admin;
    }

    /**
     * 创建命名空间，如果存在，则不创建
     * @param namespace
     */
    protected void createNamespaceNX(String namespace) throws IOException {
        Admin admin = getAdmin();
        try {
            admin.getNamespaceDescriptor(namespace);
        }catch (NamespaceNotFoundException e){
            //不存在，则创建
            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespace).build();
            admin.createNamespace(namespaceDescriptor);
        }
    }

    /**
     * 创建表，如果存在，则删除
     * @param name
     * @param families
     */
    protected void createTableXX(String name,String coprocessorClass,Integer regionCount,String... families) throws IOException {
        //获取连接和表对象
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);

        //存在则删除
        if(admin.tableExists(tableName)){
            deleteTable(name);
        }

        //创建表
        createTable(name, coprocessorClass,regionCount,families);
    }

    /**
     * 创建一个分区的表
     * @param name
     * @param families
     * @throws IOException
     */
    protected void createTableXX(String name,String... families) throws IOException {
        createTableXX(name, null,null, families);
    }

    /**
     * 插入数据
     * @param tablename
     * @param put
     * @throws IOException
     */
    protected  void putData(String tablename, Put put) throws IOException {
        //1.获取表对象
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(tablename));

        //2.添加put对象
        table.put(put);

        //3.关闭表
        table.close();

    }

    /**
     * 插入put数据集合
     * @param tablename
     * @param puts
     * @throws IOException
     */
    protected  void putData(String tablename, List<Put> puts) throws IOException {
        //1.获取表对象
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(tablename));

        //2.添加put对象
        table.put(puts);

        //3.关闭表
        table.close();

    }

    /**
     * 插入数据
     * @param obj
     */
    protected void putData(Object obj) throws IOException {
        //1.获取表名
        Class clazz = obj.getClass();
        TableRef tableRef = (TableRef) clazz.getAnnotation(TableRef.class);
        String tableName = tableRef.value();

        //2.获取表对象
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(tableName));

        //3.获取rowkey
        Field[] fields = clazz.getDeclaredFields();
        String rowkeyString = "";
        for (Field field : fields) {
            Rowkey rowkey = field.getAnnotation(Rowkey.class);
            if(rowkey != null){
                try {
                    field.setAccessible(true);
                    rowkeyString = (String) field.get(obj);
                    break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        //4.创建put对象
        Put put = new Put(Bytes.toBytes(rowkeyString));
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if(column != null){
                String colFamily = column.colFamily();
                String colName = column.colName();
                String colValue = "";

                if("".equals(colName)){
                    colName = field.getName();
                }

                try {
                    field.setAccessible(true);
                    colValue = (String) field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                //添加列
                put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(colName),Bytes.toBytes(colValue));
            }
        }

        //5.添加数据
        table.put(put);

        //6.关闭表
        table.close();

    }

    /**
     * 获取查询时候startrow，stoprow集合
     * @param tel
     * @param start
     * @param end
     * @return
     */
    protected  List<String[]> getStartStopRowkeys(String tel,String start,String end){
        List<String[]> rowkeyss = new ArrayList<String[]>();

        //取前6位
        String startDate = start.substring(0,6);
        String endDate = end.substring(0,6);

        //开始时间
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(DateUtil.parse(startDate,"yyyyMM"));

        //结束时间
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(DateUtil.parse(endDate, "yyyyMM"));

        //计算当前月份的起始rowkey
        while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()){
            String nowDate = DateUtil.format(startCal.getTime(), "yyyyMM");
            String startrow = genRegionNum(tel, nowDate) + "_" + tel + "_" + nowDate;
            String endrow = startrow + "|";

            String[] rowkeys = {startrow,endrow};

            rowkeyss.add(rowkeys);

            //月份加1
            startCal.add(Calendar.MONTH, 1);
        }

        return rowkeyss;
    }

    /**
     * 获取分区号
     * @param tel
     * @param calltime
     * @return
     */
    protected   int genRegionNum(String tel,String calltime){
        String telCode = tel.substring(4);
        String calltimeCode = calltime.substring(0, 6);

        int telHash = telCode.hashCode();
        int calltimeHash = calltimeCode.hashCode();

        int crc = Math.abs(telHash ^ calltimeHash);

        return crc % ValueConstant.REGION_COUNT;
    }

    /**
     * 创建表
     * @param name
     * @param regionCount
     * @param coprocessorClass
     * @param families
     * @throws IOException
     */
    private void createTable(String name,String coprocessorClass,Integer regionCount,String... families) throws IOException {
        //列族为空，默认为info
        if(families == null || families.length == 0){
            families = new String[1];
            families[0] = Names.CF_INFO.getValue();
        }

        //获取连接和表对象
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);

        //创建表描述器
        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);

        //添加列族
        for (String family : families) {
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(family);
            tableDescriptor.addFamily(columnDescriptor);
        }

        //添加协处理器
        if(coprocessorClass != null && !"".equals(coprocessorClass)){
            tableDescriptor.addCoprocessor(coprocessorClass);
        }

        //创建分区表
        if (regionCount == null || regionCount <= 1){
            admin.createTable(tableDescriptor);
        }else {
            //分区建
            byte[][] splitkeys = genSplitkeys(regionCount);
            admin.createTable(tableDescriptor,splitkeys);
        }


    }

    /**
     * 创建分区建
     * @param regionCount
     * @return
     */
    private  byte[][] genSplitkeys(Integer regionCount) {
        //分区建个数
        int splitkeyCount = regionCount - 1;
        byte[][] bs = new byte[splitkeyCount][];

        List<byte[]> bsList = new ArrayList<byte[]>();
        for (int i = 0; i < splitkeyCount; i++) {
            String key = i + "|";
            bsList.add(Bytes.toBytes(key));
        }

        //排序
        Collections.sort(bsList, new Bytes.ByteArrayComparator());

        bsList.toArray(bs);

        //返回
        return  bs;
    }

    /**
     * 删除表
     * @param name
     * @throws IOException
     */
    private void deleteTable(String name) throws IOException {
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);

        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }

    /**
     * 关闭连接和admin
     * @throws IOException
     */
    protected void close() throws IOException {
        Connection conn = connHolder.get();
        if(conn != null){
            conn.close();
            connHolder.remove();
        }

        Admin admin = adminHolder.get();
        if(admin != null){
            admin.close();
            adminHolder.remove();
        }
    }
}
