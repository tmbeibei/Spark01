package com.atguigu.chapter10.work4

import scala.collection.mutable

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    //设置一个映射，其中包含你想要的一些装备，以及它们的价格。然后根据这个映射构建另一个新映射，采用同一组键，但是价格上打9折。
    var map1 = mutable.Map("手枪" -> 100, "机枪" -> 250.3, "飞机" -> 240)
    var map2 = new mutable.HashMap[String, Double]

    /*   for((key,value)<-map1){
         var price = value * 0.9
         println(key,value)
       }*/

    var arr = Array(1, 55, 2, 3, 66, 21)
    val t1 = minmax(arr)
    println("min:" + t1._1 + "\t max:" + t1._2)

  }

  def minmax(values: Array[Int]): (Int, Int) = {
    (values.min, values.max)
  }
}
