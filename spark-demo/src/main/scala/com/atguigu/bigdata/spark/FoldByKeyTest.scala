package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/26 0026
 * @notes
 */
object FoldByKeyTest {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")

    val sc = new SparkContext(config)

    val listRDD: RDD[(Int, Int)] = sc.parallelize(List((1,3),(1,2),(1,4),(2,3),(3,6),(3,8)),3)

    val foldRDD: RDD[(Int, Int)] = listRDD.foldByKey(0)(Math.max)

    foldRDD.collect().foreach(println)
  }

}
