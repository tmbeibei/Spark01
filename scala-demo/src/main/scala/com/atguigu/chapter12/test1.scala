package com.atguigu.chapter12

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test1 {
  def main(args: Array[String]): Unit = {
    val ch = '+'
    val res = ch match{
      case '+' => "ABC"
      case mychar => "ok " + mychar
    }

    println("res=" + res)
  }
}
