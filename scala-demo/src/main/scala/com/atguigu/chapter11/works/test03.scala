package com.atguigu.chapter11.works

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test03 {
  def main(args: Array[String]): Unit = {
    val lst = List(5,3,7,4)
    val lst2 = lst.map(num2)
    println(lst2)

  }

  def num2(num:Int): Int ={
    num * 2
  }
}
