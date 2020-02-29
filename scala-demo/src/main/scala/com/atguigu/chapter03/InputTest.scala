package com.atguigu.chapter03

import scala.io.StdIn

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object InputTest {
  def main(args: Array[String]): Unit = {
    // 从控制台输入姓名 、年龄 、薪水
    println("请输入姓名:")
    val name = StdIn.readLine()
    println("请输入年龄:")
    val age = StdIn.readInt()
    println("请输入薪水:")
    val sal = StdIn.readDouble()

    println(s"姓名:$name\t年龄:$age\t薪水:$sal")

    scala.io.StdIn
  }
}
