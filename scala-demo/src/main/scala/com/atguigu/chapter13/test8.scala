package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test8 {
  def main(args: Array[String]): Unit = {
    println(test(sum,25))
  }

  def sum(value:Double): Double ={
    value * 2
  }

  /**
   * 高阶函数，接受一个函数，并且返回值
   * @param f
   * @param num
   * @return
   */
  def test(f:Double=>Double,num:Double): Double ={
    f(num)
  }


}


