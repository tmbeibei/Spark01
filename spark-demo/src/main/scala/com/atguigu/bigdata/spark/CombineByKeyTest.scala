package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/26 0026
 * @notes
 */
object CombineByKeyTest {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")

    val sc = new SparkContext(config)

    val listRDD: RDD[(String, Int)] = sc.parallelize(Array(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98)),2)


    val restRDD: RDD[(String, (Int, Int))] = listRDD.combineByKey((_,1),
      (acc:(Int,Int),v)=>(acc._1+v,acc._2+1),
      (acc1:(Int,Int),acc2:(Int,Int))=>(acc1._1+acc2._1,acc1._2+acc2._2))

    val res: RDD[(String, Int)] = restRDD.map {
      case (a, b) => {
        (a, b._1 / b._2)
      }
    }


    res.collect().foreach(println)
  }

}
