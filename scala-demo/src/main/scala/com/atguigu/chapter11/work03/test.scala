package com.atguigu.chapter11.work03

import scala.collection.mutable.ArrayBuffer

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    val words = "AABBCCDD"
    val lst = new ArrayBuffer[Char]()
    words.foldLeft(lst)(putArray);
    println(lst)
  }

  /**
   * 把字符加到集合中
   * @param arr
   * @param c
   * @return
   */
  def putArray(arr:ArrayBuffer[Char],c:Char): ArrayBuffer[Char] ={
    arr.append(c)
    arr
  }
}
