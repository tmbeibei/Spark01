package com.atguigu.chapter10

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test04 {
  def main(args: Array[String]): Unit = {
    val tuple01 = (4,"45x","tom",7)

    println(tuple01._1)
    println(tuple01.productElement(0))

  }
}
