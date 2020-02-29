package com.atguigu.bigdata.test001

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{Cell, CellUtil, HBaseConfiguration}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/27 0027
 * @notes
 */
object HbaseTest {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("HbaseTest")
    val sc = new SparkContext(config)

    // 1 配置Hbase
    val conf: Configuration = HBaseConfiguration.create()
    conf.set(TableInputFormat.INPUT_TABLE, "student")

    // 2 从Hbase读取数据
    val hbaseRDD: RDD[(ImmutableBytesWritable, Result)] = sc.newAPIHadoopRDD(
      conf,
      classOf[TableInputFormat],
      classOf[ImmutableBytesWritable],
      classOf[Result]
    )

    println("总数:" + hbaseRDD.count())

    hbaseRDD.foreach {
      case (rowkey, result) => {
        val cells: Array[Cell] = result.rawCells()
        for (cell <- cells) {
          println("主键:" + Bytes.toString(CellUtil.cloneRow(cell)))
          println("列族:" + Bytes.toString(CellUtil.cloneFamily(cell)))
          println("列名:" + Bytes.toString(CellUtil.cloneQualifier(cell)))
          println("  值:" + Bytes.toString(CellUtil.cloneValue(cell)))
        }
      }
    }

    sc.stop()

  }
}
