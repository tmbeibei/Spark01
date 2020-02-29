package com.atguigu.bigdata.test001

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.mapred.JobConf
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/27 0027
 * @notes
 */
object HbaseTest02 {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("HbaseTest")
    val sc = new SparkContext(config)

    // 1 配置Hbase
    val conf: Configuration = HBaseConfiguration.create()
    val jobConf = new JobConf(conf)
    jobConf.setOutputFormat(classOf[TableOutputFormat])
    jobConf.set(TableOutputFormat.OUTPUT_TABLE, "student")

    val listRDD: RDD[(String, String, String)] = sc.makeRDD(List(("1013", "li1", "20"), ("1014", "li2", "21"), ("1015", "li3", "25")))

    // 构建(ImmutableBytesWritable, Put) 对象
    val mapRDD: RDD[(ImmutableBytesWritable, Put)] = listRDD.map {
      case (rowkey, name, age) => {
        val rowkeyByte: Array[Byte] = Bytes.toBytes(rowkey)
        val immutableBytesWritable = new ImmutableBytesWritable(rowkeyByte)
        val put = new Put(rowkeyByte)
        val family: Array[Byte] = Bytes.toBytes("info")
        put.addColumn(family, Bytes.toBytes("name"), Bytes.toBytes(name))
        put.addColumn(family, Bytes.toBytes("age"), Bytes.toBytes(age))
        (immutableBytesWritable, put)
      }
    }

    // 保存到hbase
    mapRDD.saveAsHadoopDataset(jobConf)


    sc.stop()

  }
}
