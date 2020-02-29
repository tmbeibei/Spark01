package com.atguigu.chapter12

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test5 {
  def main(args: Array[String]): Unit = {
    val arrs = Array((0, 1), (1, 0), (10, 30), (1, 1), (1, 0, 2))
    for (item <- arrs) {
      val result = item match {
        case (0, _) => "0..."
        case (y, 0) => y
        case (x, y) => (y, x)
        case _ => "other"
      }
      println("res=" + result)
    }
  }

}
