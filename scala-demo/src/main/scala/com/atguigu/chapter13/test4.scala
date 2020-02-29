package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test4 {
  def main(args: Array[String]): Unit = {
    val lst = List(1, 2, 3, 4, "abc")
    println(lst.collect(f2))

    println(lst.collect {
      case i: Int => i + 1
    })
  }

  def f2: PartialFunction[Any, Int] = {
    case i: Int => i + 1
  }

}
