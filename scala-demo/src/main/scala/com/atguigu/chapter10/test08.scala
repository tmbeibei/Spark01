package com.atguigu.chapter10

import scala.collection.mutable

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test08 {
  def main(args: Array[String]): Unit = {
    // 方式4
    val map4 = mutable.Map(("zs",10),("lisi",55),("zmusi",45),(11,22))

    map4("BB") = 20
    println(map4)

    map4 +=("CC" -> 50)
    map4 +=("DD" -> 50,"EE"->60)
    println(map4)
    map4 -=("AA","BB","CC","DD")
    println(map4)

    println("--------------------")
    for((key,value)<-map4){
      println(key + "\t" + value)
    }
  }
}
