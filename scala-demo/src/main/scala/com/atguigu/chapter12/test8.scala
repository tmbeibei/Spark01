package com.atguigu.chapter12

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test8 {
  def main(args: Array[String]): Unit = {
    List(1, 3, 5, 9) match {
      case first :: second :: rest => {
        println(s"$first $second ${rest.length} + $rest")
      }
      case _ => println("匹配不成功")
    }
  }

}
