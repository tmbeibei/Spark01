package com.atguigu.chapter01

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes 公共函数
 */
object Common {
  def main(args: Array[String]): Unit = {
    println("Hello,World")
  }

  /**
   * 求2个数的和
   * @example 输入10,20, return：30
   * @param n1  整数1
   * @param n2  整数2
   * @return 和
   */
  def sum(n1:Int,n2:Int): Int ={
    return n1 + n2
  }

}
