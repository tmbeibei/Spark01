package com.atguigu.chapter10

import scala.collection.mutable.ArrayBuffer

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test02 {
  def main(args: Array[String]): Unit = {
    var arr = new ArrayBuffer[Int]()
    arr.append(10)
    arr.append(23)
    arr.append(11)
    arr.append(15)

    for(item <- arr){
      println(item)
    }
  }

}
