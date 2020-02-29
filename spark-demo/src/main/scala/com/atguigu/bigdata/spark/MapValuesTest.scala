package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/26 0026
 * @notes
 */
object MapValuesTest {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")

    val sc = new SparkContext(config)

    val listRDD: RDD[(Int, String)] = sc.parallelize(Array((1,"a"),(1,"d"),(2,"b"),(3,"c")))

    val res: RDD[(Int, String)] = listRDD.mapValues(_+"|||")

    res.collect().foreach(println)
  }

}
