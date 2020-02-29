package com.atguigu.chapter12

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test2 {
  def main(args: Array[String]): Unit = {
    val a = 3
    val obj = if (a == 1) 1
    else if (a == 2) "2"
    else if (a == 3) BigInt(3)
    else if (a == 4) Map("aa" -> 1)
    else if (a == 5) Map(1 -> "AA")
    else if (a == 6) Array(1, 2, 3)
    else if (a == 7) Array("aa", 1)
    else if (a == 8) Array("aa")

    println("obj=" + obj)

    val result = obj match {
      case a: Int => a
      case b: Map[String, Int] => "对象是一个字符串、数字的集合"
      case c: Map[Int, String] => "对象是一个数字，字符串的集合"
      case d: Array[String] => "对象是一个字符串数组"
      case e: Array[Int] => "对象是一个数字数组"
      case f: BigInt => Int.MaxValue
      case _ => "啥都不是"
    }

    println("result:" + result)
  }
}
