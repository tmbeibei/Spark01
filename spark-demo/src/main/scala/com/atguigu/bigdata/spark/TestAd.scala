package com.atguigu.bigdata.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author tianmin
 * @date 2020/2/26 0026
 * @notes
 */
object TestAd {
  /*
  1516609143867 6 7 64 16
  1516609143869 9 4 75 18
  数据结构：时间戳，省份，城市，用户，广告，中间字段使用空格分割。
   */
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test1")

    val sc = new SparkContext(config)

    // 1 读取文件
    val listFile: RDD[String] = sc.textFile("E:\\test\\words.txt")

    // 2 组成((省份,广告),1)
    val listMap: RDD[((String, String), Int)] = listFile.map { x =>
      val fields: Array[String] = x.split(" ")
      ((fields(1), fields(4)), 1)
    }

    /*
    ((1,25),15)
    ((0,7),5)
    ((5,10),15)
    ((3,4),12)
     */
    // 3 按(省份,广告) 分组统计
    val res: RDD[((String, String), Int)] = listMap.reduceByKey(_ + _)

    // 4 组成 省份,(广告，次数)
    /*
    (1,(25,15))
    (0,(7,5))
    (5,(10,15))
     */
    val res2: RDD[(String, (String, Int))] = res.map { x =>
      (x._1._1, (x._1._2, x._2))
    }

    // 5
    /**
     * (4,CompactBuffer((12,25), (25,11), (27,13), (13,21), (8,21), (1,20), (6,15), (7,7), (3,15), (22,18), (10,10), (19,10), (9,19), (23,18), (18,17), (11,11), (28,16), (26,10), (2,22), (29,13), (17,14), (16,22), (5,14), (21,17), (0,14), (15,17), (24,18), (4,17), (20,11), (14,18)))
     * (8,CompactBuffer((8,18), (13,19), (16,10), (17,17), (23,14), (19,18), (24,17), (2,27), (9,20), (7,19), (11,22), (0,17), (5,19), (21,15), (18,18), (1,13), (4,16), (25,12), (15,17), (29,17), (14,18), (28,15), (3,18), (10,15), (12,18), (26,13), (6,13), (20,23), (22,11), (27,18)))
     */
    val res3: RDD[(String, Iterable[(String, Int)])] = res2.groupByKey()

    // 6
    val res4: RDD[(String, Iterable[(String, Int)])] = res3.mapValues { x =>
      x.toList.sortWith((x, y) => x._2 > y._2).take(3)
    }

    res4.collect().foreach(println)
  }

}
