package com.atguigu.chapter12

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test7 {
  def main(args: Array[String]): Unit = {
    val map = Map("A" -> 1, "B" -> 0, "C" -> 3, "DD" -> 0)
    for ((k, v) <- map) {
      println(k + " " + v)
    }
    println("============================")

    for ((key, 0) <- map) {
      println(key)
    }

    println("=======================")
    for ((key, value) <- map if value == 0) {
      println(key)
    }
  }
}
