package com.atguigu.chapter12.work4

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test2 {
  def main(args: Array[String]): Unit = {
    val amt1 = new Currency(3000.0,"RMB")
    val amt2 = amt1.copy(5000.4)
    println(amt2)
    println(amt1)
  }
}
