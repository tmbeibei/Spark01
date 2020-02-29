package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test9 {
  def main(args: Array[String]): Unit = {
    val res = minuesxy(3)(5)
    println(res)
  }

  /**
   * 返回了一个匿名函数
   * @param x
   * @return
   */
  def minuesxy(x:Int) ={
    (y:Int)=>x -y
  }

}
