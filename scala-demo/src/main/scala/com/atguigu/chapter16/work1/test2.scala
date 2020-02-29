package com.atguigu.chapter16.work1

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
object test2 {
  def main(args: Array[String]): Unit = {
    /*val res = values((x: Int) => x * x, -5, 5)
    println(res)*/
    val max = largest((x: Int) => 10 * x - x * x, (1 to 10))
    println("max=" + max)
  }

  /*
  编写函数values(fun:(Int)=>Int,low:Int,high:Int),
  该函数输出一个集合，对应给定区间内给定函数的输入和输出。
  比如，values(x=>x*x,-5,5)应该产出一个对偶的集合(-5,25),(-4,16),(-3,9),…,(5,25)
   */

  def values(fun: (Int) => Int, low: Int, high: Int) = {
    var newList = List[(Int, Int)]()
    for (item <- low to high) {
      newList = newList :+ (item, fun(item))
    }
    newList
  }

  /*
  5、编写函数largest(fun:(Int)=>Int,inputs:Seq[Int]),
  输出在给定输入序列中给定函数的最大值。
  举例来说，largest(x=>10*x-x*x,1 to 10)应该返回25.不得使用循环或递归
   */

  def largest(fun: Int => Int, inputs: Seq[Int]) = {
    inputs.map(fun).max
  }
}
