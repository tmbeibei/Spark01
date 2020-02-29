package com.atguigu.chapter11.work03

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test04 {
  def main(args: Array[String]): Unit = {
    val stream1 = numsForm(5)
    println(stream1)
  }

  def numsForm(num:BigInt): Stream[BigInt] ={
    num #:: numsForm(num + 1)
  }
}
