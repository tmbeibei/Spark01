package com.atguigu.chapter12.work2

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    val number = 0.0
    number match {
      case Square(n) => println("匹配成功N=" + n)
      case _ => println("没匹配成功")
    }
  }
}
