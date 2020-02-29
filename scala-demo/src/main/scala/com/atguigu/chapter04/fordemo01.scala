package com.atguigu.chapter04

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object fordemo01 {
  def main(args: Array[String]): Unit = {
    val start = 1
    val stop = 10
    for (i <- start until  stop){
      println(s"你好，尚硅谷 $i")
    }

    /*val list = List("hell","wold",30,25.00)
    for(item <- list){
      println(s"item=$item")
    }*/
  }
}
