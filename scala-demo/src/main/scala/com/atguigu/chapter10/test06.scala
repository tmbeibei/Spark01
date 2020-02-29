package com.atguigu.chapter10

import scala.collection.mutable

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test06 {
  def main(args: Array[String]): Unit = {
    var q1 = new mutable.Queue[Any]()
    q1 += 100
    q1 ++= List(5,7,1)

    println(q1.head)
    println(q1.last)
    println(q1.tail)

    for(item <- q1.tails){
      println("item=" + item)
    }
  }
}
