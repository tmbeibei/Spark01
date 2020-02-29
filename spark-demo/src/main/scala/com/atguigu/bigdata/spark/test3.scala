package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/26 0026
 * @notes
 */
object test3 {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")

    val sc = new SparkContext(config)

    val listRDD: RDD[String] = sc.parallelize(Array("one","two","tian","tom","one","tian","jack","one"))

    val mapRDD: RDD[(String, Int)] = listRDD.map((_,1))

    val groupRDD: RDD[(String, Iterable[Int])] = mapRDD.groupByKey()

    val mapResultRDD: RDD[(String, Int)] = groupRDD.map(item=>(item._1,item._2.sum))

    mapResultRDD.collect().foreach(println)
  }

}
