package com.atguigu.chapter10

import scala.collection.mutable.ArrayBuffer

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test03 {
  def main(args: Array[String]): Unit = {
    val  arr = ArrayBuffer("1","2")
    import scala.collection.JavaConversions.bufferAsJavaList
    val javaArr = new ProcessBuilder(arr)
    val arrList =  javaArr.command()
    println(arrList)
  }
}
