package com.atguigu.chapter05

/**
 * @author tianmin
 * @date 2020/2/19 0019
 * @notes
 */
object Lzaydemo01 {
  def main(args: Array[String]): Unit = {
    lazy val res = sum(10,30)
    println("-------------------------")

    println(s"res=$res")
  }

  def sum(num1:Int,num2:Int): Int ={
    println("函数被执行了")
    num1 + num2
  }
}
