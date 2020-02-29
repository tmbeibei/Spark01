package com.atguigu.chapter10

import scala.collection.mutable

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test07 {
  def main(args: Array[String]): Unit = {
    // 方式1 不可变，但是有序
    val map1 = Map("zs" -> 10,"lisi" -> 25,"wz" ->15)
    println(map1)

    // 方式2 可变，无序
    val map2 = mutable.Map("zs" -> 10,"lisi" -> 25,"wz" ->15)
    println(map2)

    // 方式3
    val map3 = new mutable.HashMap[String,Int]()
    map3.put("zs",10)
    map3.put("lisi",25)
    map3.put("wz",15)
    println(map3)

    // 方式4
    val map4 = mutable.Map(("zs",10),("lisi",55),("zmusi",45),(11,22))
    println(map4)




  }
}
