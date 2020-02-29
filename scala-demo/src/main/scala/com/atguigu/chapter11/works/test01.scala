package com.atguigu.chapter11.works

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test01 {
  def main(args: Array[String]): Unit = {
    val res = test(num2 _,6);
    println(res)

    //val f1 = myPrint _
  }

  def num2(num:Double): Double ={
    num * 2
  }

  def test(f:Double=>Double,num:Double): Double ={
    f(num)
  }

  def myPrint(): Unit ={
    println("Hello world")
  }
}
