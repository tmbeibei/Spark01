package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/26 0026
 * @notes
 */
object test2 {
  def main(args: Array[String]): Unit = {
    // 1 创建配置文件
    val config = new SparkConf().setMaster("local[*]").setAppName("SparkRDD")

    // 2 获取上下文环境
    val sc = new SparkContext(config)

    val arr = Array((1,"aaa"),(2,"bbb"),(3,"ccc"),(4,"dddd"))

    val listRDD: RDD[(Int, String)] = sc.parallelize(arr,4)

    println("分区数:" + listRDD.partitions.size)

    val partRDD: RDD[(Int, String)] = listRDD.partitionBy(new HashPartitioner(2))

    println("分区数:" + partRDD.partitions.size)

    partRDD.saveAsTextFile("output")

  }

}
