package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test7 {
  def main(args: Array[String]): Unit = {
    val trap = (n1: Int, n2: Int) => n1 + n2
    println(trap(3,9))
    println("类型:" + trap)
  }

}
