package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test3 {
  def main(args: Array[String]): Unit = {
    val lst = List(1,2,3,4,"abc")

    val addOne = new PartialFunction[Any, Int] {
      override def isDefinedAt(x: Any): Boolean = x.isInstanceOf[Int]

      override def apply(v1: Any): Int = {
        v1.asInstanceOf[Int] + 1
      }
    }
    println(lst.collect(addOne))
  }
}
