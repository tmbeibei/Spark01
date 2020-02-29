package com.atguigu.chapter10

import scala.collection.mutable

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test09 {
  def main(args: Array[String]): Unit = {
    val set1 = Set(1, 3, 6, 2, 2)
    println(set1)

    val set2 = mutable.Set(1, 3, 6, 2, 2)
    println(set2)

    set2.add(8);
    set2 += 89
    set2 += 1
    set2 -= 890
    set2.remove(1)
    println(set2)
    for (value <- set2) {
      println(value)
    }

  }
}
