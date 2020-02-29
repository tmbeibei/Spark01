package com.atguigu.chapter09

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
object test01 {
  def main(args: Array[String]): Unit = {
    var num:Int = 4.5
    println("num1:" + num)
  }

  implicit def f1(num:Double): Int ={
    num.toInt
  }
}
