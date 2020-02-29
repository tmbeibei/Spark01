package com.atguigu.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/25 0025
 * @notes
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    // 1 创建SparkConfig
    val config = new SparkConf().setMaster("local[*]").setAppName("wordCount")

    // 2  创建SparkContext
    val sc = new SparkContext(config)

    // 3 读取文件
    val lines = sc.textFile("file:///opt/module/spark-2.4.5/input")

    // 4 扁平化
    val words = lines.flatMap(_.split(" "))

    // 5 组成元祖
    val wordToOne = words.map((_,1))

    // 5 分组统计
    val wordToSum = wordToOne.reduceByKey(_+_)

    // 6 打印
    wordToSum.collect().foreach(println)

  }
}
