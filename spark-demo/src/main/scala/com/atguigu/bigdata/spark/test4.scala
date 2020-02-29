package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/26 0026
 * @notes
 */
object test4 {
  def main(args: Array[String]): Unit = {
    // 1 创建配置文件
    val config = new SparkConf().setMaster("local[*]").setAppName("SparkRDD")

    // 2 获取上下文环境
    val sc = new SparkContext(config)

    val listRDD: RDD[Int] = sc.parallelize(1 to 20)
    val res: Array[Int] = listRDD.take(4)
    res.foreach(println)
  }

}
