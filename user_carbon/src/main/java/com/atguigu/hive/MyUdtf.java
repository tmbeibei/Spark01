package com.atguigu.hive;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianmin
 * @date 2020/2/12 0012
 * @notes
 */
public class MyUdtf extends GenericUDTF {

    private List<String> dataList = new ArrayList<String>();
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        List<String> fieldNames = new ArrayList<String>();
        List<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();

        fieldNames.add("wordname");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    /**
     * 核心方法
     * @param args
     * @throws HiveException
     */
    public void process(Object[] args) throws HiveException {
        String line = args[0].toString();
        String splitKey = args[1].toString();

        String[] words = line.split(splitKey);

        for (String word : words) {
            dataList.clear();
            dataList.add(word);
            forward(dataList);
        }
    }

    public void close() throws HiveException {

    }
}
