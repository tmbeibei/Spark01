package com.atguigu.mr3;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author tianmin
 * @date 2020/2/17 0017
 * @notes
 */
public class Fruit3Mapper extends TableMapper<ImmutableBytesWritable, Put> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        // 1 定义PUt
        Put put = new Put(key.get());

        // 2 封装Put
        for (Cell cell : value.rawCells()) {
            if("name".equals(Bytes.toString(CellUtil.cloneQualifier(cell)))){
                put.add(cell);
            }
        }

        // 3 写出
        context.write(key, put);
    }
}
