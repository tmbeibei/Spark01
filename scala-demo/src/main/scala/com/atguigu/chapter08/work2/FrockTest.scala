package com.atguigu.chapter08.work2

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
object FrockTest {
  def main(args: Array[String]): Unit = {
    println("序号:" + Frock.getNextNum())
    println("序号:" + Frock.getNextNum())

    val frock1 = new Frock
    println("序列号:" + frock1.getSerialNumber)
    val frock2 = new Frock
    println("序列号:" + frock2.getSerialNumber)
    val frock3 = new Frock
    println("序列号:" + frock3.getSerialNumber)

  }
}
