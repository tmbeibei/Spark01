package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/25 0025
 * @notes
 */
object SparkRDD {
  def main(args: Array[String]): Unit = {
    // 1 创建配置文件
    val config = new SparkConf().setMaster("local[*]").setAppName("SparkRDD")

    // 2 获取上下文环境
    val sc = new SparkContext(config)

    // 3 创建RDD
    val list: RDD[Int] = sc.makeRDD(List(1,2,3,4,5))

    val list2: RDD[Int] = sc.parallelize(Array(1,3,5,2))


  }
}
