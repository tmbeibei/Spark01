package com.atguigu.chapter06

import scala.io.StdIn

/**
 * @author tianmin
 * @date 2020/2/19 0019
 * @notes
 */
object demo02 {
  def main(args: Array[String]): Unit = {
    val box = new Box
    println("请输入盒子的长：")
    box.len = StdIn.readDouble()

    println("请输入盒子的宽：")
    box.width = StdIn.readDouble()

    println("请输入盒子的高：")
    box.height = StdIn.readDouble()

    printf("盒子的体积:%.2f", box.volume())
  }
}

class Box {
  var len: Double = _
  var width: Double = _
  var height: Double = _

  def volume(): Double = {
    len * width * height
  }

}
