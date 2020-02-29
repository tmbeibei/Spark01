package com.atguigu.chapter12.wrok01

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    val oper = '+'
    val num1 = 20
    val num2 = 10
    var res = 0
    oper match {
      case '+' => {
        res = num1 + num2
      }
      case '-' => {
        res = num1 - num2
      }
      case '*' => {
        res = num1 * num2
      }
      case '/' => {
        res = num1 / num2
      }
      case _ => println("error")

    }

    println("res=" + res)
  }

}
