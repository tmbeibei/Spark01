package com.atguigu.chapter04

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object demo2 {
  def main(args: Array[String]): Unit = {
    var num = 6
    for(i <- 0 to 6){
      println(s"$i + $num = ${i + num}" )
      num -= 1;
    }
  }
}
