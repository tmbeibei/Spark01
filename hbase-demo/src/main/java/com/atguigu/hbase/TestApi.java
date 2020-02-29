package com.atguigu.hbase;




import org.junit.After;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/16 0016
 * @notes
 */
public class TestApi {


    private HbaseDemo hbaseDemo = new HbaseDemo();

    @Test
    void getCollection() {
    }

    @Test
    void getAdmin() {
    }

    @Test
    void tableExists() {
    }

    @Test
    void createNamespace() {
    }

    @Test
    void createTableXX() throws IOException {

        hbaseDemo.createTableXX("big:stu", "info");
    }

    @Test
    void deleteTable() {
    }

    @After
    void close() {
        hbaseDemo.close();
    }

    @Test
    void testPutData() throws IOException {
        hbaseDemo.putData("big:stu", "1002", "info", "name", "sunwukong");
        hbaseDemo.putData("big:stu", "1002", "info", "age", "30");
    }

    @Test
    void testgetData() throws IOException {
        hbaseDemo.getData("big:stu", "1002");
    }

    @Test
    void testgetData2() throws IOException {
        hbaseDemo.getData("big:stu", "1002","info");
    }

    @Test
    void testgetData3() throws IOException {
        hbaseDemo.getData("big:stu", "1002","info","name");
    }

    @Test
    void testGetScan() throws IOException{
        hbaseDemo.getScan("big:stu", "1001", "info", "name");
    }

    @Test
    void testdeleteData() throws IOException{
        hbaseDemo.deleteData("big:stu", "1002", "info", "name");
    }
}
