package com.atguigu.chapter07.work1

import scala.io.StdIn

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
object test02 {
  def main(args: Array[String]): Unit = {
    println("输入圆的半径:")
    val radius = StdIn.readDouble()

    println("输入圆柱的高度:")
    val len = StdIn.readDouble()

    // 1 封装
    val cylinder = new Cylinder
    cylinder.setRadius(radius)
    cylinder.setLen(len)

    // 2 打印
    printf("圆柱体的体积是:%.2f",cylinder.findVolume())
  }
}
