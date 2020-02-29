package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/26 0026
 * @notes
 */
object SortByKeyTest {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")

    val sc = new SparkContext(config)

    val listRDD: RDD[(Int, String)] = sc.parallelize(Array((3,"aa"),(6,"cc"),(2,"bb"),(1,"dd")))

    val res: RDD[(Int, String)] = listRDD.sortByKey(true)

    res.collect().foreach(println)
  }

}
