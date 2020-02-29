package com.atguigu.chapter12

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test3 {
  def main(args: Array[String]): Unit = {
    //    val arrs = Array(Array(1), Array(1, 0), Array(0, 1, 0), Array(1, 1, 0), Array(0, 1, 0, 1))
    //    for (item <- arrs) {
    //      val res = item match {
    //        case Array(0) => "0"
    //        case Array(x, y) => x + " " + y
    //        case Array(0, _*) => "以零开头的数组"
    //        case _ => "啥都不是"
    //      }
    //      println("res=" + res)
    //    }

    val arr = Array(100, 30)
    val res = arr match {
      case Array(x, y) => Array(y, x)
    }
    println(res(0) + " " + res(1))
  }
}
