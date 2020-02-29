package com.atguigu.chapter12.wrok01

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test3 {
  def main(args: Array[String]): Unit = {
    val abj = 10
    val result = abj match {
      case a: Int => a
      //case b: Map[String, Int] => "Map结婚"
      case _ => "啥都不是"
    }

    println(result)
  }
}
