package com.atguigu.chapter12.work3

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    val namesString = "Alice,Bob,Thomas,jack"
    namesString match {
      case Names(first, second, thread) => {
        println("匹配成功")
        println(s"$first $second $thread")
      }
      case _ => println("nothing")
    }
  }
}


