package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/26 0026
 * @notes
 */
object ReduceByKeyTest {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")

    val sc = new SparkContext(config)

    val listRDD: RDD[(String, Int)] = sc.parallelize(List(("male",5),("female",3),("male",23),("female0k",3)))

    val reducerRDD: RDD[(String, Int)] = listRDD.reduceByKey(_+_)

    reducerRDD.collect().foreach(println)
  }

}
