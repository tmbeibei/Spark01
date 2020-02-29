package com.atguigu.chapter11.work03

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test02 {
  def main(args: Array[String]): Unit = {
    var words = List("atguigu hen hello","atguigu hen aaa aaa aaa ccc ddd uuu")
    val res = new ListBuffer[String]
    val map = mutable.Map[String,Int]()
    words.foldLeft(res)(mt01).foldLeft(map)(charCount)
    println(map)
  }

  def charCount(map:mutable.Map[String,Int],str: String): mutable.Map[String,Int] ={
    map +=(str -> (map.getOrElse(str,0) + 1))
  }

  def mt01(lst:ListBuffer[String],str:String): ListBuffer[String] ={
    val words = str.split(" ")
    for(item <- words){
      lst += item
    }
    lst
  }
}
