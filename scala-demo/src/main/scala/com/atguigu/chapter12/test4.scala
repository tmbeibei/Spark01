package com.atguigu.chapter12

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test4 {
  def main(args: Array[String]): Unit = {

    val temp = 0 :: Nil
    println(temp)

    var arrs = Array(List(0), List(1, 0), List(88), List(0, 0, 0), List(1, 0, 0))
    for (item <- arrs) {
      val result = item match {
        case 0 :: Nil => "0"
        case x :: y :: Nil => x + " " + y
        case 0 :: tail => "0..."
        case x :: Nil => x
        case _ => "something else"
      }
      println(result)
    }
  }

}
