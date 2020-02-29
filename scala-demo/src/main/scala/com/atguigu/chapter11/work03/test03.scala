package com.atguigu.chapter11.work03

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test03 {
  def main(args: Array[String]): Unit = {
    val lst1 = List(1,2,3)
    val lst2 = List(4,5,6)
    val lst3 = lst1.zip(lst2)
    println(lst3(0)._1)
  }
}
