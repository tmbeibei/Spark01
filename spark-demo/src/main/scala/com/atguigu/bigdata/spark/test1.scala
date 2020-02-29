package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/26 0026
 * @notes
 */
object test1 {
  def main(args: Array[String]): Unit = {
    // 1 创建配置文件
    val config = new SparkConf().setMaster("local[*]").setAppName("SparkRDD")

    // 2 获取上下文环境
    val sc = new SparkContext(config)

    //2.创建一个pairRDD，计算相同key对应值的相加结果
    val lst: RDD[(Int, Int)] = sc.parallelize(List((1,3),(1,2),(1,4),(2,3),(3,6),(3,8)),3)

    val foldRDD: RDD[(Int, Int)] = lst.foldByKey(0)(_+_)



    foldRDD.glom().collect().foreach(arr=>println(arr.mkString("|")))
  }
}
