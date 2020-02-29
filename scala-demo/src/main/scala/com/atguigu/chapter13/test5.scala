package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test5 {
  def main(args: Array[String]): Unit = {
    val arr = Array(1,2,3,4)
    val arr2 = arr.map(plus).mkString(",,,")

    //println(arr2)
    println(plus _)
  }

  def plus(value:Int): Int ={
    value + 3
  }

}
