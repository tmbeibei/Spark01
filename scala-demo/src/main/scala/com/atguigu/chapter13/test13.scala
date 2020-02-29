package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test13 {
  def main(args: Array[String]): Unit = {
    var x = 10
    myUtil(x >0){
      x -= 1
      println("x=" + x)
    }
  }

  def myUtil(condition:  => Boolean)(block:  => Unit): Unit = {
    if (condition) {
      block
      myUtil(condition)(block)
    }
  }

}
