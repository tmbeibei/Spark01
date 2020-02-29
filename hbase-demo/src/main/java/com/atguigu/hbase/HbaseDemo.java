package com.atguigu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author tianmin
 * @date 2020/2/16 0016
 * @notes
 */
public class HbaseDemo {

    private ThreadLocal<Connection> connHolder = new ThreadLocal<Connection>();
    private ThreadLocal<Admin> adminHolder = new ThreadLocal<Admin>();

    /**
     * 获取连接
     * @return
     * @throws IOException
     */
    public synchronized Connection getCollection() throws IOException {
        // 1 获取连接
        Connection conn = connHolder.get();

        // 2 不存在着创建新连接
        if(conn == null){
            Configuration conf = HBaseConfiguration.create();
            conn= ConnectionFactory.createConnection(conf);
            connHolder.set(conn);
        }

        // 3 返回连接
        return conn;
    }

    /**
     * 返回admin对象
     * @return
     * @throws IOException
     */
    public synchronized Admin getAdmin() throws IOException{
        // 1 获取admin
        Admin admin = adminHolder.get();

        // 2 不存在则创建
        if(admin == null){
            admin = getCollection().getAdmin();
            adminHolder.set(admin);
        }

        // 3 返回
        return admin;
    }

    /**
     * 判断表是否存在
     * @param tableName
     * @return
     * @throws IOException
     */
    public boolean tableExists(String tableName) throws IOException {
        return  getAdmin().tableExists(TableName.valueOf(tableName));
    }

    /**
     * 创建名称空间
     * @param namespace
     * @throws IOException
     */
    public void createNamespace(String namespace) throws IOException {
        // 1 获取连接
        Admin admin = getAdmin();

        try {
            admin.getNamespaceDescriptor(namespace);
        }catch (NamespaceNotFoundException e){
            // 2 创建名称空间
            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespace).build();
            admin.createNamespace(namespaceDescriptor);
        }
    }

    /**
     * 创建表，如果存在则删除,重新创建
     * @param tablename
     * @param columnFamilies
     */
    public void createTableXX(String tablename,String ... columnFamilies) throws IOException {
        // 1 判断列族
        if(columnFamilies == null || columnFamilies.length == 0){
            System.out.println("必须传列族信息");
            return;
        }

        // 2 判断表是否存在，存在则删除
        if(tableExists(tablename)){
            deleteTable(tablename);
        }

        // 3 新建表
        Admin admin = getAdmin();

        // 3.1表描述器
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tablename));

        // 3.2 循环添加列族
        for (String columnFamily : columnFamilies) {
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(columnFamily);
            tableDescriptor.addFamily(columnDescriptor);
        }

        // 3.3 执行创建
        admin.createTable(tableDescriptor);
    }

    /**
     * 删除表
     * @param name
     */
    public void deleteTable(String name) throws IOException {
        // 0 判断表是否存在
        if(!tableExists(name)){
            return;
        }

        // 1 获取admin对象
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);

        // 2 禁用表
        admin.disableTable(tableName);

        // 3 删除表
        admin.deleteTable(tableName);
    }

    /**
     * 插入数据
     * @param tablename
     * @param rowkey
     * @param columnFamily
     * @param column
     * @param value
     */
    public void putData(String tablename,String rowkey,String columnFamily,String column,String value) throws IOException {
        // 1 获取Connection对象
        Connection conn = getCollection();

        // 2 获取Table对象
        Table table = conn.getTable(TableName.valueOf(tablename));

        // 3 创建put对象
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));

        table.put(put);

        // 4 关闭table
        table.close();
    }

    /**
     * 获取数据get
     * @param tablename
     * @param rowkey
     * @throws IOException
     */
    public void  getData(String tablename,String rowkey) throws IOException{
        // 1 获取connection对象
        Connection conn = getCollection();

        // 2 获取table对象
        Table table = conn.getTable(TableName.valueOf(tablename));

        // 3 创建get对象
        Get get = new Get(Bytes.toBytes(rowkey));

        Result result = table.get(get);
        for (Cell cell : result.rawCells()) {
            System.out.println("列族:" + Bytes.toString(CellUtil.cloneFamily(cell)));
            System.out.println("列名:" + Bytes.toString(CellUtil.cloneQualifier(cell)));
            System.out.println("  值:" + Bytes.toString(CellUtil.cloneValue(cell)));
        }

        // 4 关闭table
        table.close();
    }

    public void  getData(String tablename,String rowkey,String columnFamily) throws IOException{
        // 1 获取connection对象
        Connection conn = getCollection();

        // 2 获取table对象
        Table table = conn.getTable(TableName.valueOf(tablename));

        // 3 创建get对象
        Get get = new Get(Bytes.toBytes(rowkey));
        get.addFamily(Bytes.toBytes(columnFamily));

        Result result = table.get(get);
        for (Cell cell : result.rawCells()) {
            System.out.println("列族:" + Bytes.toString(CellUtil.cloneFamily(cell)));
            System.out.println("列名:" + Bytes.toString(CellUtil.cloneQualifier(cell)));
            System.out.println("  值:" + Bytes.toString(CellUtil.cloneValue(cell)));
        }

        // 4 关闭table
        table.close();
    }

    public void  getData(String tablename,String rowkey,String columnFamily,String column) throws IOException{
        // 1 获取connection对象
        Connection conn = getCollection();

        // 2 获取table对象
        Table table = conn.getTable(TableName.valueOf(tablename));

        // 3 创建get对象
        Get get = new Get(Bytes.toBytes(rowkey));
        //get.addFamily(Bytes.toBytes(columnFamily));
        get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));

        Result result = table.get(get);
        for (Cell cell : result.rawCells()) {
            System.out.println("列族:" + Bytes.toString(CellUtil.cloneFamily(cell)));
            System.out.println("列名:" + Bytes.toString(CellUtil.cloneQualifier(cell)));
            System.out.println("  值:" + Bytes.toString(CellUtil.cloneValue(cell)));
        }

        // 4 关闭table
        table.close();
    }

    /**
     * scan获取数据
     * @param tablename
     * @param rowkey
     * @param columnFamily
     * @param columnname
     * @throws IOException
     */
    public void getScan(String tablename,String rowkey,String columnFamily,String columnname) throws IOException{
        // 1 获取connection对象
        Connection conn = getCollection();

        // 2 获取table对象
        Table table = conn.getTable(TableName.valueOf(tablename));

        // 3 创建Scan对象
        Scan scan = new Scan();
        //scan.addFamily(Bytes.toBytes(columnFamily));
        scan.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columnname));

        // 3 获取数据
        ResultScanner resultScanner = table.getScanner(scan);
        Iterator<Result> iterator = resultScanner.iterator();
        while (iterator.hasNext()){
            Result result = iterator.next();
            for (Cell cell : result.rawCells()) {
                System.out.print("rowkey:" + Bytes.toString(CellUtil.cloneRow(cell)));
                System.out.print("\tfamily:" + Bytes.toString(CellUtil.cloneFamily(cell)));
                System.out.print("\tcolumn:" + Bytes.toString(CellUtil.cloneQualifier(cell)));
                System.out.println("\tvalue:" + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }

    }

    public void deleteData(String tablename,String rowkey,String columnFamily,String columname) throws IOException{
        // 1 获取连接
        Connection conn = getCollection();

        // 2 获取表对象
        Table table = conn.getTable(TableName.valueOf(tablename));

        // 3 创建Delete
        Delete delete = new Delete(Bytes.toBytes(rowkey));
        //delete.addColumns(Bytes.toBytes(columnFamily), Bytes.toBytes(columname));

        // 3 删除
        table.delete(delete);

    }


    /**
     * 关闭连接
     */
    public void close(){
        // 1 关闭admin
        Admin admin = adminHolder.get();
        if(admin != null){
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 2 关闭connection
        Connection conn = connHolder.get();
        if(conn != null){
            try {
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
