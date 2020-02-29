package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test10 {
  def main(args: Array[String]): Unit = {
    val lst = List(1, 2, 3, 4, 5)
    println(lst.map((x: Int) => x + 1))
    println(lst.map(x => x + 1))
    println(lst.map(_ + 1))
  }

}
